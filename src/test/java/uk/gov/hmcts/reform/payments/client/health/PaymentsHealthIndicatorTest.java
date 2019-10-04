package uk.gov.hmcts.reform.payments.client.health;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.payments.client.config.PaymentClientAutoConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {
        PaymentsHealthIndicator.class,
        PaymentClientAutoConfiguration.class,
        PaymentsHealthIndicatorTest.StubAuthTokenGenerator.class
})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@AutoConfigureWireMock(port = 8091)
class PaymentsHealthIndicatorTest {
    @Autowired
    private PaymentsHealthIndicator healthIndicator;

    @Test
    void testHealthCheckUp() {
        stubFor(get(urlEqualTo("/health/liveness"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"status\":\"UP\"}")));

        Health health = healthIndicator.health();
        assertNotNull(health);
        assertEquals(Status.UP, health.getStatus());
    }

    @Test
    void testHealthCheckDown() {
        stubFor(get(urlEqualTo("/health/liveness"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"status\":\"DOWN\"}")));

        Health health = healthIndicator.health();
        assertNotNull(health);
        assertEquals(Status.DOWN, health.getStatus());
    }

    @Test
    void testFailedHealthCheck() {
        stubFor(get(urlEqualTo("/health/liveness"))
                .willReturn(aResponse().withStatus(HttpStatus.BAD_REQUEST.value())));

        Health health = healthIndicator.health();
        assertNotNull(health);
        assertEquals(Status.DOWN, health.getStatus());
    }

    @Component
    public static class StubAuthTokenGenerator implements AuthTokenGenerator {
        @Override
        public String generate() {
            return "auth token";
        }
    }
}
