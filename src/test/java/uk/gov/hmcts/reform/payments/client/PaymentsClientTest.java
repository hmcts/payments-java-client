package uk.gov.hmcts.reform.payments.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.payments.client.request.CardPaymentRequest;
import uk.gov.hmcts.reform.payments.client.request.CreditAccountPaymentRequest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentsClientTest {
    private static final CardPaymentRequest CARD_PAYMENT_REQUEST = CardPaymentRequest.builder()
            .amount(BigDecimal.valueOf(999.99))
            .caseReference("case reference")
            .ccdCaseNumber("ccd case number")
            .channel("channel")
            .currency("currency")
            .description("description")
            .provider("provider")
            .caseType("Case Type")
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
}
