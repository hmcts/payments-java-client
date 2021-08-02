package uk.gov.hmcts.reform.payments.client;

import feign.Request;
import feign.error.FeignExceptionConstructor;
import feign.error.ResponseBody;
import feign.error.ResponseHeaders;

import java.util.Collection;
import java.util.Map;

public class InvalidPaymentRequestException extends Exception {
    private final Request request;
    private final String body;
    private final Map<String, Collection<String>> headers;

    @FeignExceptionConstructor
    public InvalidPaymentRequestException(Request request,
                                          @ResponseBody String body,
                                          @ResponseHeaders Map<String,
                                                  Collection<String>> headers) {
        this.request = request;
        this.body = body;
        this.headers = headers;
    }
}
