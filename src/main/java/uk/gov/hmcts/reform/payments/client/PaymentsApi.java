package uk.gov.hmcts.reform.payments.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import uk.gov.hmcts.reform.payments.client.health.InternalHealth;
import uk.gov.hmcts.reform.payments.client.models.PaymentDto;
import uk.gov.hmcts.reform.payments.client.request.CardPaymentRequest;
import uk.gov.hmcts.reform.payments.client.request.CreditAccountPaymentRequest;

@FeignClient(name = "payments-api", url = "${payments.api.url}")
public interface PaymentsApi {
    @GetMapping("/health")
    InternalHealth health();

    @PostMapping(value = "/credit-account-payments", consumes = "application/json")
    PaymentDto createCreditAccountPayment(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("ServiceAuthorization") String serviceAuthorization,
            @RequestBody CreditAccountPaymentRequest paymentRequest
    );

    @PostMapping(value = "/card-payments", consumes = "application/json")
    PaymentDto createCardPayment(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("ServiceAuthorization") String serviceAuthorization,
            @RequestHeader("return-url") String redirectUrl,
            @RequestHeader("service-callback-url") String serviceCallbackUrl,
            @RequestBody CardPaymentRequest paymentRequest
    );

    @GetMapping("/card-payments/{paymentReference}")
    PaymentDto retrieveCardPayment(
            @PathVariable("paymentReference") String paymentReference,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("ServiceAuthorization") String serviceAuthorization
    );

    @PostMapping(value = "/card-payments/{paymentReference}/cancel")
    void cancelCardPayment(
            @PathVariable("paymentReference") String paymentReference,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("ServiceAuthorization") String serviceAuthorization
    );
}
