package uk.gov.hmcts.reform.payments.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.payments.client.models.PaymentDto;
import uk.gov.hmcts.reform.payments.request.CardPaymentRequest;
import uk.gov.hmcts.reform.payments.request.CreditAccountPaymentRequest;
import uk.gov.hmcts.reform.payments.request.PaymentServiceRequest;
import uk.gov.hmcts.reform.payments.request.ServiceRequestPayment;
import uk.gov.hmcts.reform.payments.response.PaymentServiceResponse;

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

    public PaymentServiceResponse createServiceRequest(String authorisation, PaymentServiceRequest paymentRequest) {
        return paymentsApi.createServiceRequest(
                authorisation,
                authTokenGenerator.generate(),
                paymentRequest
        );
    }

    public PaymentDto createPbaPayment(String serviceReqReference, String authorisation,
                                       ServiceRequestPayment paymentRequest) {
        return paymentsApi.createPbaPayment(
                serviceReqReference,
                authorisation,
                authTokenGenerator.generate(),
                paymentRequest
        );
    }
}
