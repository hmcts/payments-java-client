package uk.gov.hmcts.reform.payments.client.functional.env;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String scope;
    private String idToken;
    private String tokenType;
    private String expiresIn;
    private String apiAuthToken;
}
