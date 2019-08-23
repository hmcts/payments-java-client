package uk.gov.hmcts.reform.payments.client.functional.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "payments", name = "api.url")
@EnableFeignClients(basePackages = "uk.gov.hmcts.reform.payments.client")
public class PaymentClientAutoConfiguration {
}
