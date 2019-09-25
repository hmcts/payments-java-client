package uk.gov.hmcts.reform.payments.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.payments.client.models.PaymentDto;

@Service
public class PaymentsClient {
    private PaymentsApi paymentsApi;
    private AuthTokenGenerator authTokenGenerator;

    @Autowired
    public PaymentsClient(PaymentsApi paymentsApi, AuthTokenGenerator authTokenGenerator) {
        this.paymentsApi = paymentsApi;
        this.authTokenGenerator = authTokenGenerator;
    }

    public PaymentDto createPayment(String authorisation, CardPaymentRequest paymentRequest, String redirectUrl) {
        return paymentsApi.create(
                authorisation,
                authTokenGenerator.generate(),
                redirectUrl,
                paymentRequest
        );
    }

    public PaymentDto retrievePayment(String authorisation, String paymentReference) {
        return paymentsApi.retrieve(
                paymentReference,
                authorisation,
                authTokenGenerator.generate()
        );
    }
}
