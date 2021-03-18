package uk.gov.hmcts.reform.payments.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.gov.hmcts.reform.authorisation.ServiceAuthorisationApi;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.payments.client.models.FeeDto;
import uk.gov.hmcts.reform.payments.client.models.PaymentDto;
import uk.gov.hmcts.reform.payments.request.CardPaymentRequest;
import uk.gov.hmcts.reform.payments.request.CreditAccountPaymentRequest;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {
        PaymentsApi.class,
        PaymentsClient.class,
        PaymentsClientWiremockTest.ServiceTestSupportAuthTokenGenerator.class,
        PaymentsClientWiremockTest.StubServiceAuthorisationImpl.class
})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@AutoConfigureWireMock(port = 8091)
class PaymentsClientWiremockTest {
    @Autowired
    private ServiceTestSupportAuthTokenGenerator authTokenGenerator;

    @Autowired
    private PaymentsApi paymentsApi;

    private PaymentsClient paymentsClient;

    @BeforeEach
    void setupClient() {
        this.paymentsClient = new PaymentsClient(paymentsApi, authTokenGenerator);
    }

    @Test
    void testCreateCreditAccountPayment() {
        PaymentDto payment = paymentsClient.createCreditAccountPayment(
                "Authorisation",
                CreditAccountPaymentRequest.builder()
                        .amount(BigDecimal.TEN)
                        .caseReference("e1880a05-740e-4a71-a82a-4931cc09f791")
                        .ccdCaseNumber("UNKNOWN")
                        .currency("GBP")
                        .description("description")
                        .service("my service")
                        .siteId("AA00")
                        .fees(new FeeDto[0])
                        .accountNumber("PBA1234567")
                        .customerReference("customer reference")
                        .organisationName("organisation name")
                        .build()
        );
        assertNotNull(payment);
        assertAll(
            () -> assertEquals("RC-1566-2080-1331-6611", payment.getReference()),
            () -> assertEquals("Initiated", payment.getStatus()),
            () -> assertEquals("3pev48jhsin6n1sr91lgvi9j5o", payment.getExternalReference()),
            () -> assertEquals("2020-15662080132", payment.getPaymentGroupReference()),
            () -> assertNotNull(payment.getLinks()),
            () -> assertAll(
                () -> assertNotNull(payment.getLinks().getNextUrl()),
                () -> assertEquals(
                        "https://www.payments.service.gov.uk/secure/473320d7-4db4-42a5-935f-8bcaf0bd2058",
                        payment.getLinks().getNextUrl().getHref().toString()
                ),
                () -> assertEquals(RequestMethod.GET, payment.getLinks().getNextUrl().getMethod())
            )
        );
    }

    @Test
    void testCreateCardPayment() {
        PaymentDto payment = paymentsClient.createCardPayment(
                "Authorisation",
                CardPaymentRequest.builder()
                        .amount(BigDecimal.TEN)
                        .caseReference("e1880a05-740e-4a71-a82a-4931cc09f790")
                        .ccdCaseNumber("UNKNOWN")
                        .currency("GBP")
                        .description("description")
                        .fees(new FeeDto[0])
                        .build(),
                "http://localhost",
                "http://localhost"
        );
        assertNotNull(payment);
        assertAll(
            () -> assertEquals("RC-1566-2080-1331-6610", payment.getReference()),
            () -> assertEquals("Initiated", payment.getStatus()),
            () -> assertEquals("3pev48jhsin6n1sr91lgvi9j3o", payment.getExternalReference()),
            () -> assertEquals("2019-15662080132", payment.getPaymentGroupReference()),
            () -> assertNotNull(payment.getLinks()),
            () -> assertAll(
                () -> assertNotNull(payment.getLinks().getNextUrl()),
                () -> assertEquals(
                        "https://www.payments.service.gov.uk/secure/473320d7-4db4-42a5-935f-8bcaf0bd2056",
                        payment.getLinks().getNextUrl().getHref().toString()
                ),
                () -> assertEquals(RequestMethod.GET, payment.getLinks().getNextUrl().getMethod())
            )
        );
    }

    @Test
    void testRetrieveCardPayment() {
        PaymentDto payment = paymentsClient.retrieveCardPayment("Authorisation", "RC-1566-2093-5462-0545");
        assertNotNull(payment);
        assertAll(
            () -> assertEquals("RC-1566-2093-5462-0545", payment.getReference()),
            () -> assertEquals(BigDecimal.TEN, payment.getAmount()),
            () -> assertEquals("description", payment.getDescription()),
            () -> assertEquals("GBP", payment.getCurrency()),
            () -> assertEquals("online", payment.getChannel()),
            () -> assertEquals("card", payment.getMethod()),
            () -> assertEquals("gov pay", payment.getExternalProvider()),
            () -> assertNotNull(payment.getLinks()),
            () -> assertAll(
                () -> assertEquals(
                        "http://localhost:4421/card-payments/RC-1566-2093-5462-0545",
                        payment.getLinks().getSelf().getHref().toString()
                ),
                () -> assertEquals(RequestMethod.GET, payment.getLinks().getSelf().getMethod())
            )
        );
    }

    @Test
    void testCancelCardPayment() {
        // test passes if no exceptions are thrown
        paymentsClient.cancelCardPayment("Authorisation", "RC-7238-3245-0193-7732");
    }

    @Service
    static class ServiceTestSupportAuthTokenGenerator implements AuthTokenGenerator {
        @Override
        public String generate() {
            return "let me in";
        }
    }

    @Service
    static class StubServiceAuthorisationImpl implements ServiceAuthorisationApi {

        @Override
        public String serviceToken(Map<String, String> signIn) {
            return null;
        }

        @Override
        public void authorise(String authHeader, String[] roles) {

        }

        @Override
        public String getServiceName(String authHeader) {
            return null;
        }
    }
}
