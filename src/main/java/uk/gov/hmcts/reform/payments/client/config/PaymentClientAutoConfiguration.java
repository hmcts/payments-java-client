package uk.gov.hmcts.reform.payments.client.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.payments.client.PaymentsApi;
import uk.gov.hmcts.reform.payments.client.health.PaymentsHealthIndicator;

@Configuration
@ConditionalOnProperty(prefix = "payments", name = "api.url")
@EnableFeignClients(basePackages = "uk.gov.hmcts.reform.payments.client")
public class PaymentClientAutoConfiguration {

    @Bean
    public PaymentsHealthIndicator paymentsHealthIndicator(PaymentsApi paymentsApi) {
        return new PaymentsHealthIndicator(paymentsApi);
    }
}
