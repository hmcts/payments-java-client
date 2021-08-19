package uk.gov.hmcts.reform.payments.client.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import uk.gov.hmcts.reform.payments.client.InvalidPaymentRequestException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static feign.FeignException.errorStatus;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    public static final String DUPLICATE_PAYMENT = "Duplicate Payment";

    @Override
    public Exception decode(String methodKey, Response response) {
        String responseBody = getResponseBodyString(response);
        log.error("Response body from Payment client is {}", responseBody);

        if (response.status() == 400 && responseBody.equals(DUPLICATE_PAYMENT)) {
            log.error("Error took place when using Feign client to send HTTP Request."
                    + " Status code "
                    + response.status()
                    + ", methodKey = "
                    + methodKey);
            return new InvalidPaymentRequestException(responseBody);
        }
        return errorStatus(methodKey, response);
    }

    private String getResponseBodyString(Response response) {
        try {
            return StreamUtils.copyToString(response.body().asInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "error read response body. " + e.getMessage();
        }
    }
}
