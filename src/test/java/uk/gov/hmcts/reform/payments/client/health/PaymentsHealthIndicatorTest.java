package uk.gov.hmcts.reform.payments.client.health;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.hmcts.reform.payments.client.config.PaymentClientAutoConfiguration;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {
        PaymentClientAutoConfiguration.class
})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "uk.gov.hmcts.reform.payments.client")
class PaymentsHealthIndicatorTest {

    @RegisterExtension
    public static final WireMockExtension wireMockExtension = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().port(8091))
            .build();

    @Autowired
    private PaymentsHealthIndicator healthIndicator;

    @Test
    void testHealthCheckUp() {
        wireMockExtension.stubFor(get(urlEqualTo("/health"))
                .withId(UUID.randomUUID())
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"status\":\"UP\"}")));

        Health health = healthIndicator.health();
        assertNotNull(health);
        assertEquals(Status.UP, health.getStatus());
    }

    @Test
    void testHealthCheckDown() {
        wireMockExtension.stubFor(get(urlEqualTo("/health"))
                .withId(UUID.randomUUID())
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"status\":\"DOWN\"}")));

        Health health = healthIndicator.health();
        assertNotNull(health);
        assertEquals(Status.DOWN, health.getStatus());
    }

    @Test
    void testFailedHealthCheck() {
        wireMockExtension.stubFor(get(urlEqualTo("/health"))
                .withId(UUID.randomUUID())
                .willReturn(aResponse().withStatus(HttpStatus.BAD_REQUEST.value())));

        Health health = healthIndicator.health();
        assertNotNull(health);
        assertEquals(Status.DOWN, health.getStatus());
    }
}
