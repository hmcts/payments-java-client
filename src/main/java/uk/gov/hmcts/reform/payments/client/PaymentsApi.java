package uk.gov.hmcts.reform.payments.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import uk.gov.hmcts.reform.payments.client.models.PaymentDto;

@FeignClient(name = "payments-api", url = "${payments.api.url}")
public interface PaymentsApi {
    @PostMapping(value = "/card-payments", consumes = "application/json")
    PaymentDto create(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("ServiceAuthorization") String serviceAuthorization,
            @RequestHeader("return-url") String redirectUrl,
            @RequestBody CardPaymentRequest paymentRequest
    );

    @GetMapping("/card-payments/{paymentReference}")
    PaymentDto retrieve(
            @PathVariable("paymentReference") String paymentReference,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("ServiceAuthorization") String serviceAuthorization
    );
}
