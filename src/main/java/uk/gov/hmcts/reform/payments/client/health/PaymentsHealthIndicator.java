package uk.gov.hmcts.reform.payments.client.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class PaymentsHealthIndicator implements HealthIndicator {
    private String healthEndpoint;

    @Autowired
    public PaymentsHealthIndicator(@Value("${payments.api.url}") String paymentsDomain) {
        healthEndpoint = paymentsDomain + "/health/liveness";
    }

    @Override
    public Health health() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));

            HttpEntity<?> entity = new HttpEntity<Object>("", httpHeaders);

            ResponseEntity<InternalHealth> exchange = new RestTemplate().exchange(
                    healthEndpoint,
                    HttpMethod.GET,
                    entity,
                    InternalHealth.class);

            InternalHealth body = exchange.getBody();

            return new Health.Builder(body.getStatus()).build();
        } catch (Exception ex) {
            return Health.down(ex).build();
        }
    }
}
