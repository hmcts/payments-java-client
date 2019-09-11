package uk.gov.hmcts.reform.payments.client.functional.env;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginDetails {
    private final String username;
    private final String password;
}
