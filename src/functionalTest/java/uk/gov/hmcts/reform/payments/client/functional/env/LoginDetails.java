package uk.gov.hmcts.reform.payments.client.functional.env;

import lombok.Data;

@Data
public class LoginDetails {
    private final String username;
    private final String password;
}
