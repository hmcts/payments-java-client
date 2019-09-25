package uk.gov.hmcts.reform.payments.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentsClientTest {
    private static final CardPaymentRequest PAYMENT_REQUEST = CardPaymentRequest.builder()
            .amount(BigDecimal.valueOf(999.99))
            .caseReference("case reference")
            .ccdCaseNumber("ccd case number")
            .channel("channel")
            .currency("currency")
            .description("description")
            .provider("provider")
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
    void createPaymentShouldInvokePaymentsApi() {
        client.createPayment("authorisation", PAYMENT_REQUEST, "redirect");

        verify(paymentsApi).create("authorisation", "auth token", "redirect", PAYMENT_REQUEST);
    }

    @Test
    void retrievePaymentShouldInvokePaymentsApi() {
        client.retrievePayment("authorisation", "payment reference");

        verify(paymentsApi).retrieve("payment reference", "authorisation", "auth token");
    }

    @Test
    void createPaymentShouldPropagateExceptions() {
        when(authTokenGenerator.generate())
                .thenThrow(new RuntimeException("expected exception for create payment"));

        assertThatThrownBy(() -> client.createPayment("authorisation", PAYMENT_REQUEST, "redirect"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("expected exception for create payment");
    }

    @Test
    void retrievePaymentShouldPropagateExceptions() {
        when(authTokenGenerator.generate())
                .thenThrow(new RuntimeException("expected exception for retrieve payment"));

        assertThatThrownBy(() -> client.retrievePayment("authorisation", "payment reference"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("expected exception for retrieve payment");
    }
}
