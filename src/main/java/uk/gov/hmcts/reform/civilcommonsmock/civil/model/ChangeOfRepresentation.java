package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ChangeOfRepresentation {

    @JsonProperty("organisationToRemoveID")
    private String organisationToRemoveID;
    @JsonProperty("organisationToAddID")
    private String organisationToAddID;
    @JsonProperty("caseRole")
    private String caseRole;
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    @JsonProperty("formerRepresentationEmailAddress")
    private String formerRepresentationEmailAddress;

}
