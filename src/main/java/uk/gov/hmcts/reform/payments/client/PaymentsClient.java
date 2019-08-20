package uk.gov.hmcts.reform.payments.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.payments.client.models.Payment;

@Service
public class PaymentsClient {
    private PaymentsApi paymentsApi;
    private AuthTokenGenerator authTokenGenerator;

    @Autowired
    public PaymentsClient(PaymentsApi paymentsApi, AuthTokenGenerator authTokenGenerator) {
        this.paymentsApi = paymentsApi;
        this.authTokenGenerator = authTokenGenerator;
    }

    public Payment createPayment(String authorisation, PaymentRequest paymentRequest, String redirectUrl) {
        String serviceAuth = authTokenGenerator.generate();
        return paymentsApi.create(
                "Bearer " + authorisation,
                "Bearer " + serviceAuth,
                redirectUrl,
                paymentRequest
        );
    }

    public Payment retrievePayment(String authorisation, String paymentReference) {
        return paymentsApi.retrieve(
                paymentReference,
                "Bearer " + authorisation,
                "Bearer " + authTokenGenerator.generate()
        );
    }
}
