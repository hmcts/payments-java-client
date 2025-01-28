package uk.gov.hmcts.reform.payments.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.payments.client.models.CasePaymentRequestDto;
import uk.gov.hmcts.reform.payments.client.models.FeeDto;
import uk.gov.hmcts.reform.payments.request.CardPaymentRequest;
import uk.gov.hmcts.reform.payments.request.CardPaymentServiceRequestDTO;
import uk.gov.hmcts.reform.payments.request.CreateServiceRequestDTO;
import uk.gov.hmcts.reform.payments.request.CreditAccountPaymentRequest;
import uk.gov.hmcts.reform.payments.request.PBAServiceRequestDTO;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentsClientTest {

    private static final BigDecimal TEN_2_DP = new BigDecimal("10.00");

    private static final String CCD_CASE_NUMBER = "UNKNOWN";
    private static final BigDecimal FEE_AMOUNT = TEN_2_DP;
    private static final String FEE_CODE = "FEE0234";
    private static final String FEE_DESCRIPTION = "A sample fee";
    private static final String FEE_REFERENCE = "reference";
    private static final String FEE_VERSION = "version";
    private static final Integer FEE_VOLUME = 1;
    private static final String JURISDICTION_1 = "jurisdiction 1";
    private static final String JURISDICTION_2 = "jurisdiction 2";
    private static final String MEMO_LINE = "Memo line";
    private static final String NATURAL_ACCOUNT_CODE = "natural account code";
    private static final BigDecimal NET_FEE_AMOUNT = TEN_2_DP;

    private static final CardPaymentRequest CARD_PAYMENT_REQUEST = CardPaymentRequest.builder()
            .amount(BigDecimal.valueOf(999.99))
            .caseReference("case reference")
            .ccdCaseNumber("ccd case number")
            .channel("channel")
            .currency("currency")
            .description("description")
            .provider("provider")
            .build();

    private static final CreditAccountPaymentRequest CREDIT_ACCOUNT_PAYMENT = CreditAccountPaymentRequest.builder()
            .accountNumber("PBA1234567")
            .amount(BigDecimal.valueOf(999.99))
            .caseReference("case reference")
            .ccdCaseNumber("ccd case number")
            .currency("currency")
            .customerReference("customer reference")
            .description("description")
            .organisationName("organisation name")
            .service("service")
            .siteId("site ID")
            .build();

    private static final CardPaymentServiceRequestDTO CARD_PAYMENT_SERVICE_REQUEST =
            CardPaymentServiceRequestDTO.builder()
                    .returnUrl("return-url")
                    .language("English")
                    .amount(new BigDecimal("232.00"))
                    .currency("GBP")
                    .build();

    private static final CreateServiceRequestDTO SERVICE_REQUEST = CreateServiceRequestDTO.builder()
            .callBackUrl("callbackurl")
            .casePaymentRequest(CasePaymentRequestDto.builder().action("action").responsibleParty("party").build())
            .caseReference("case reference")
            .ccdCaseNumber("ccd case number")
            .fees(new FeeDto[]{
                    FeeDto.builder()
                            .id(1)
                            .calculatedAmount(FEE_AMOUNT)
                            .ccdCaseNumber(CCD_CASE_NUMBER)
                            .code(FEE_CODE)
                            .description(FEE_DESCRIPTION)
                            .jurisdiction1(JURISDICTION_1)
                            .jurisdiction2(JURISDICTION_2)
                            .memoLine(MEMO_LINE)
                            .naturalAccountCode(NATURAL_ACCOUNT_CODE)
                            .netAmount(NET_FEE_AMOUNT)
                            .reference(FEE_REFERENCE)
                            .version(FEE_VERSION)
                            .volume(FEE_VOLUME)
                            .build()
            })
            .hmctsOrgId("organisation id")
            .build();

    private static final PBAServiceRequestDTO SERVICE_REQUEST_PAYMENT = PBAServiceRequestDTO.builder()
            .accountNumber("acc number")
            .amount(FEE_AMOUNT)
            .currency("currency")
            .customerReference("customer reference")
            .idempotencyKey("key")
            .organisationName("organisation name").build();

    @Mock
    private PaymentsApi paymentsApi;

    @Mock
    private AuthTokenGenerator authTokenGenerator;

    private PaymentsClient client;

    @BeforeEach
    void setUp() {
        client = new PaymentsClient(paymentsApi, authTokenGenerator);
        when(authTokenGenerator.generate()).thenReturn("auth token");
    }

    @Test
    void createServiceRequestShouldInvokePaymentsApi() {
        client.createServiceRequest("authorisation", SERVICE_REQUEST);

        verify(paymentsApi).createServiceRequest("authorisation", "auth token", SERVICE_REQUEST);
    }

    @Test
    void createServiceRequestShouldPropagateExceptions() {
        when(authTokenGenerator.generate())
                .thenThrow(new RuntimeException("expected exception for create payment"));

        assertThatThrownBy(() -> client.createServiceRequest("authorisation", SERVICE_REQUEST))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("expected exception for create payment");
    }

    @Test
    void createGovPayCardPaymentRequest() {
        client.createGovPayCardPaymentRequest("service-request-id",
                "authorisation", CARD_PAYMENT_SERVICE_REQUEST);
        verify(paymentsApi)
                .createGovPayCardPaymentRequest("service-request-id",
                        "authorisation", "auth token", CARD_PAYMENT_SERVICE_REQUEST);
    }

    @Test
    void getGovPayCardPaymentStatus() {
        client.getGovPayCardPaymentStatus("payment-reference", "authorisation");
        verify(paymentsApi)
                .retrieveCardPaymentStatus("payment-reference", "authorisation", "auth token");
    }

    @Test
    void createCreditAccountPaymentShouldInvokePaymentsApi() {
        client.createCreditAccountPayment("authorisation", CREDIT_ACCOUNT_PAYMENT);

        verify(paymentsApi).createCreditAccountPayment("authorisation", "auth token", CREDIT_ACCOUNT_PAYMENT);
    }

    @Test
    void createCardPaymentShouldInvokePaymentsApi() {
        client.createCardPayment("authorisation", CARD_PAYMENT_REQUEST, "redirect", "serviceCallBackUrl");

        verify(paymentsApi).createCardPayment("authorisation",
                "auth token", "redirect", "serviceCallBackUrl", CARD_PAYMENT_REQUEST);
    }

    @Test
    void retrieveCardPaymentShouldInvokePaymentsApi() {
        client.retrieveCardPayment("authorisation", "payment reference");

        verify(paymentsApi).retrieveCardPayment("payment reference", "authorisation", "auth token");
    }

    @Test
    void cancelCardPaymentShouldInvokePaymentsApi() {
        client.cancelCardPayment("authorisation", "payment reference");

        verify(paymentsApi).cancelCardPayment("payment reference", "authorisation", "auth token");
    }

    @Test
    void createCardPaymentShouldPropagateExceptions() {
        when(authTokenGenerator.generate())
                .thenThrow(new RuntimeException("expected exception for create payment"));

        assertThatThrownBy(() -> client.createCardPayment("authorisation", CARD_PAYMENT_REQUEST,
                "redirect", "serviceCallBackUrl"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("expected exception for create payment");
    }

    @Test
    void createCreditAccountPaymentShouldPropagateExceptions() {
        when(authTokenGenerator.generate())
                .thenThrow(new RuntimeException("expected exception for create payment"));

        assertThatThrownBy(() -> client.createCreditAccountPayment("authorisation", CREDIT_ACCOUNT_PAYMENT))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("expected exception for create payment");
    }

    @Test
    void retrieveCardPaymentShouldPropagateExceptions() {
        when(authTokenGenerator.generate())
                .thenThrow(new RuntimeException("expected exception for retrieve payment"));

        assertThatThrownBy(() -> client.retrieveCardPayment("authorisation", "payment reference"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("expected exception for retrieve payment");
    }

    @Test
    void cancelCardPaymentShouldPropagateExceptions() {
        doThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Payment Not found"))
                .when(paymentsApi).cancelCardPayment(anyString(), anyString(), anyString());

        assertThatThrownBy(() -> client.cancelCardPayment("authorisation", "payment reference"))
                .isInstanceOf(HttpClientErrorException.class)
                .hasMessage("404 Payment Not found");
    }

    @Test
    void createPbaPaymentShouldInvokePaymentsApi() {
        client.createPbaPayment("reference", "authorisation", SERVICE_REQUEST_PAYMENT);

        verify(paymentsApi).createPbaPayment("reference", "authorisation", "auth token", SERVICE_REQUEST_PAYMENT);
    }

    @Test
    void createPbaPaymentShouldPropagateExceptions() {
        when(authTokenGenerator.generate())
                .thenThrow(new RuntimeException("expected exception for create payment"));

        assertThatThrownBy(() -> client.createPbaPayment("reference", "authorisation", SERVICE_REQUEST_PAYMENT))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("expected exception for create payment");
    }
}
