package uk.gov.hmcts.reform.payments.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.payments.client.models.PaymentDto;
import uk.gov.hmcts.reform.payments.client.request.CardPaymentRequest;
import uk.gov.hmcts.reform.payments.client.request.CreditAccountPaymentRequest;

@Service
@ConditionalOnProperty(prefix = "payments", name = "api.url")
public class PaymentsClient {
    private PaymentsApi paymentsApi;
    private AuthTokenGenerator authTokenGenerator;

    @Autowired
    public PaymentsClient(PaymentsApi paymentsApi, AuthTokenGenerator authTokenGenerator) {
        this.paymentsApi = paymentsApi;
        this.authTokenGenerator = authTokenGenerator;
    }

    public PaymentDto createCreditAccountPayment(String authorisation, CreditAccountPaymentRequest paymentRequest) {
        return paymentsApi.createCreditAccountPayment(
                authorisation,
                authTokenGenerator.generate(),
                paymentRequest
        );
    }

    public PaymentDto createCardPayment(String authorisation, CardPaymentRequest paymentRequest,
                                        String redirectUrl, String serviceCallbackUrl) {
        return paymentsApi.createCardPayment(
                authorisation,
                authTokenGenerator.generate(),
                redirectUrl,
                serviceCallbackUrl,
                paymentRequest
        );
    }

    public PaymentDto retrieveCardPayment(String authorisation, String paymentReference) {
        return paymentsApi.retrieveCardPayment(
                paymentReference,
                authorisation,
                authTokenGenerator.generate()
        );
    }

    public void cancelCardPayment(String authorisation, String paymentReference) {
        paymentsApi.cancelCardPayment(
                paymentReference,
                authorisation,
                authTokenGenerator.generate()
        );
    }
}
