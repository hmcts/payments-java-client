package uk.gov.hmcts.reform.payments.client.health;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

@ExtendWith(WireMockExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = WireMockHealthCheckTest.class)
class WireMockHealthCheckTest {

    @Value("${payments.api.url}")
    private String paymentsApiUrl;

    @RegisterExtension
    public static final WireMockExtension wireMockS = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().port(8091))
            .build();

    @BeforeEach
    void setUp() {
        assert wireMockS.getRuntimeInfo().getHttpPort() == 8091;
    }

    @Test
    void testHealthEndpoint() {
        wireMockS.stubFor(get(urlEqualTo("/health"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"status\":\"UP\"}")));

        // Use RestTemplate to call the stubbed endpoint
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(paymentsApiUrl + "/health", String.class);

        // Assert response
        assert response.getStatusCodeValue() == 200;
        assert response.getBody().contains("\"status\":\"UP\"");
    }
}