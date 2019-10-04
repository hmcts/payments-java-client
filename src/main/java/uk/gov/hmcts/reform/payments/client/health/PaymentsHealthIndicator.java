package uk.gov.hmcts.reform.payments.client.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import uk.gov.hmcts.reform.payments.client.PaymentsApi;

public class PaymentsHealthIndicator implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentsHealthIndicator.class);

    private final PaymentsApi paymentsApi;

    public PaymentsHealthIndicator(final PaymentsApi paymentsApi) {
        this.paymentsApi = paymentsApi;
    }

    @Override
    public Health health() {
        try {
            InternalHealth internalHealth = this.paymentsApi.health();
            return new Health.Builder(internalHealth.getStatus()).build();
        } catch (Exception ex) {
            LOGGER.error("Error on payments client healthcheck", ex);
            return Health.down(ex).build();
        }
    }
}
