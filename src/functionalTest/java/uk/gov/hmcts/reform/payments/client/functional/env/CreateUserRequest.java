package uk.gov.hmcts.reform.payments.client.functional.env;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    public static final String DEFAULT_PASSWORD = "Password12";
    private String email;
    private String forename;
    private String surname;
    private String id;
    private String password;
    private RoleDetail[] roles;
    private RoleDetail userGroup;
}
