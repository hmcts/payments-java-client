package uk.gov.hmcts.reform.payments.client;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.gov.hmcts.reform.authorisation.ServiceAuthorisationApi;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.payments.client.models.FeeDto;
import uk.gov.hmcts.reform.payments.client.models.PaymentDto;
import uk.gov.hmcts.reform.payments.request.CardPaymentRequest;
import uk.gov.hmcts.reform.payments.request.CardPaymentServiceRequestDTO;
import uk.gov.hmcts.reform.payments.request.CreditAccountPaymentRequest;
import uk.gov.hmcts.reform.payments.response.CardPaymentServiceRequestResponse;

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
@ComponentScan(basePackages = "uk.gov.hmcts.reform.payments.client")
class PaymentsClientWiremockTest {

    @Autowired
    private ServiceTestSupportAuthTokenGenerator authTokenGenerator;

    @Autowired
    private PaymentsApi paymentsApi;

    @RegisterExtension
    public static final WireMockExtension wireMockRule = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().port(8091))
            .build();

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
    void testCreateGovPayCardPaymentRequest() {
        CardPaymentServiceRequestResponse payment = paymentsClient.createGovPayCardPaymentRequest(
                "2023-1701090705688",
                "Authorisation",
                CardPaymentServiceRequestDTO.builder()
                        .returnUrl("return-url")
                        .language("English")
                        .amount(new BigDecimal("232.00"))
                        .currency("GBP")
                        .build()
        );
        assertNotNull(payment);
        assertAll(
            () -> assertEquals("RC-1701-0909-0602-0418", payment.getPaymentReference()),
            () -> assertEquals("lbh2ogknloh9p3b4lchngdfg63", payment.getExternalReference()),
            () -> assertEquals("Initiated", payment.getStatus()),
            () -> assertEquals("https://card.payments.service.gov.uk/secure/7b0716b2-40c4-413e-b62e-72c599c91960",
                    payment.getNextUrl())
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
    void testRetrieveCardPaymentStatus() {
        PaymentDto payment = paymentsClient.getGovPayCardPaymentStatus("RC-1701-0909-0602-0418", "Authorisation");
        assertNotNull(payment);
        assertAll(
            () -> assertEquals("RC-1701-0909-0602-0418", payment.getPaymentReference()),
            () -> assertEquals(new BigDecimal("232.00"), payment.getAmount()),
            () -> assertEquals("GBP", payment.getCurrency()),
            () -> assertEquals("online", payment.getChannel()),
            () -> assertEquals("card", payment.getMethod()),
            () -> assertEquals("gov pay", payment.getExternalProvider()),
            () -> assertEquals("Success", payment.getStatus()),
            () -> assertEquals("lbh2ogknloh9p3b4lchngdfg63", payment.getExternalReference()),
            () -> assertEquals("2023-1701090705688", payment.getPaymentGroupReference()),
            () -> assertNotNull(payment.getFees()),
            () -> assertAll(
                () -> assertEquals(
                        "FEE0336",
                        payment.getFees()[0].getCode()
                ),
                () -> assertEquals(
                        new BigDecimal("232.00"),
                        payment.getFees()[0].getCalculatedAmount()
                )
            ),
            () -> assertNotNull(payment.getStatusHistories()),
            () -> assertAll(
                () -> assertEquals(
                        "Initiated",
                        payment.getStatusHistories()[0].getStatus()
                ),
                () -> assertEquals(
                        "Success",
                        payment.getStatusHistories()[1].getStatus()
                )
            )
        );
    }

    @Test
    void testRetrieveCardPaymentStatusWithCallback() {
        PaymentDto payment = paymentsClient.getGovPayCardPaymentStatusWithCallback(
                "d1a507bc-dccd-4411-90c8-00eb248dd9a7",
                "Authorisation");
        assertNotNull(payment);
        assertAll(
                () -> assertEquals("RC-1701-0909-0602-0418", payment.getPaymentReference()),
                () -> assertEquals(new BigDecimal("232.00"), payment.getAmount()),
                () -> assertEquals("GBP", payment.getCurrency()),
                () -> assertEquals("online", payment.getChannel()),
                () -> assertEquals("card", payment.getMethod()),
                () -> assertEquals("gov pay", payment.getExternalProvider()),
                () -> assertEquals("Success", payment.getStatus()),
                () -> assertEquals("d1a507bc-dccd-4411-90c8-00eb248dd9a7", payment.getReference()),
                () -> assertEquals("lbh2ogknloh9p3b4lchngdfg63", payment.getExternalReference()),
                () -> assertEquals("2023-1701090705688", payment.getPaymentGroupReference()),
                () -> assertNotNull(payment.getFees()),
                () -> assertAll(
                        () -> assertEquals(
                                "FEE0336",
                                payment.getFees()[0].getCode()
                        ),
                        () -> assertEquals(
                                new BigDecimal("232.00"),
                                payment.getFees()[0].getCalculatedAmount()
                        )
                ),
                () -> assertNotNull(payment.getStatusHistories()),
                () -> assertAll(
                        () -> assertEquals(
                                "Initiated",
                                payment.getStatusHistories()[0].getStatus()
                        ),
                        () -> assertEquals(
                                "Success",
                                payment.getStatusHistories()[1].getStatus()
                        )
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
