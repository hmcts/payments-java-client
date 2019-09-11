package uk.gov.hmcts.reform.payments.client.functional.env;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IdamService {
    /**
     * The url to take a user to after their account is activated.
     */
    private String activationRedirectUrl;

    private String[] allowedRoles;

    /**
     * The description of the service
     */
    private String description;

    /**
     * Short Identifier for the Service
     */
    private String label;

    /**
     * The Oauth2 Client Id
     */
    private String oauth2ClientId;

    /**
     * The Oauth2 Client secret
     */
    private String oauth2ClientSecret;

    private String[] oauth2RedirectUris;

    private String oauth2Scope;

    /**
     * The endpoint used to notify the service about users on-boarding
     */
    private String onboardingEndpoint;

    private String[] onboardingRoles;

    /**
     * Flag indicating whether Self-service registration is enabled for this service. If omitted, defaults to false.
     */
    private Boolean selfRegistrationAllowed;
}
