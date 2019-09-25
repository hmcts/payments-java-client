package uk.gov.hmcts.reform.payments.client.functional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.hmcts.reform.payments.client.functional.env.UserDetails;

@Getter
@AllArgsConstructor
class User {
    private String authToken;
    private UserDetails userDetails;
}
