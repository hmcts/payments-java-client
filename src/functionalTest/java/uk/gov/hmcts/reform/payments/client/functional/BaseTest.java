package uk.gov.hmcts.reform.payments.client.functional;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import uk.gov.hmcts.reform.payments.client.PaymentsApi;
import uk.gov.hmcts.reform.payments.client.PaymentsClient;
import uk.gov.hmcts.reform.payments.client.functional.env.CreateUserRequest;
import uk.gov.hmcts.reform.payments.client.functional.env.IdamApi;
import uk.gov.hmcts.reform.payments.client.functional.env.IdamService;
import uk.gov.hmcts.reform.payments.client.functional.env.LoginDetails;
import uk.gov.hmcts.reform.payments.client.functional.env.LoginResponse;
import uk.gov.hmcts.reform.payments.client.functional.env.RoleDetail;
import uk.gov.hmcts.reform.payments.client.functional.env.TestConfiguration;
import uk.gov.hmcts.reform.payments.client.functional.env.UserDetails;

@Slf4j
@SpringBootTest(classes = {TestConfiguration.class, PaymentsClient.class, PaymentsApi.class})
public class BaseTest {
    private static final String CITIZEN_EMAIL = "autocitizen-paymentsclient-%s@hmcts.net";

    @Autowired
    protected PaymentsClient paymentsClient;

    @Autowired
    protected PaymentsApi paymentsApi;

    @Autowired
    private IdamApi idamApi;

    @Autowired
    private LoginDetails adminLoginDetails;

    protected IdamService service;

    @BeforeEach
    void prepareEnvironment() {
        LoginResponse adminSession =
                idamApi.loginUser(adminLoginDetails.getUsername(), adminLoginDetails.getPassword());
        try {
            service = idamApi.createService(
                    "AdminApiAuthToken " + adminSession.getApiAuthToken(),
                    IdamService.builder()
                            .description("pay client temporary service")
                            .label("PayClientTempService")
                            .selfRegistrationAllowed(true)
                            .oauth2ClientId("pay_client")
                            .oauth2ClientSecret("12345678")
                            .allowedRoles(new String[]{"citizen"})
                            .oauth2RedirectUris(new String[]{"http://oauth2/redirect/url"})
                            .activationRedirectUrl("http://activation/redirect/url")
                            .oauth2Scope("openid profile authorities acr roles")
                            .build()
            );
        } catch (FeignException fex) {
            if (fex.status() == HttpStatus.CONFLICT.value()) {
                log.info("Service already exists, continuing");
            } else {
                throw fex;
            }
        }
    }

    User createCitizen() {
        String email = String.format(CITIZEN_EMAIL, RandomStringUtils.random(5, true, false));
        try {
            idamApi.createUser(CreateUserRequest.builder()
                    .email(email)
                    .forename("John")
                    .surname("Smith")
                    .password(CreateUserRequest.DEFAULT_PASSWORD)
                    .userGroup(new RoleDetail("citizens"))
                    .build());
        } catch (FeignException e) {
            if (e.status() == HttpStatus.BAD_REQUEST.value()) {
                log.info("Bad request from idam, user probably already exists, continuing");
            } else {
                throw e;
            }
        }

        LoginResponse loginResponse = idamApi.loginUser(email, CreateUserRequest.DEFAULT_PASSWORD);
        UserDetails userDetails = idamApi.userInfo(loginResponse.getAccessToken());

        return new User(loginResponse.getAccessToken(), userDetails);
    }

    @AfterEach
    void tearDownEnvironment() {
        idamApi.deleteService("PayClientTempService");
    }
}
