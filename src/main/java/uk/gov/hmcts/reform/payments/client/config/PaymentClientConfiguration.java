package uk.gov.hmcts.reform.payments.client.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class PaymentClientConfiguration {

    @Bean
    @Primary
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
}
