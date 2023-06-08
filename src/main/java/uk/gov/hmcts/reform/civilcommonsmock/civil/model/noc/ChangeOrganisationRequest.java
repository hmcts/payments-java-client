package uk.gov.hmcts.reform.civilcommonsmock.civil.model.noc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import uk.gov.hmcts.reform.civilcommonsmock.ccd.model.ChangeOrganisationApprovalStatus;
import uk.gov.hmcts.reform.civilcommonsmock.ccd.model.Organisation;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.common.DynamicList;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ChangeOrganisationRequest {

    @JsonProperty("CaseRoleId")
    private DynamicList caseRoleId;

    @JsonProperty("RequestTimestamp")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime requestTimestamp;

    @JsonProperty("OrganisationToAdd")
    private Organisation organisationToAdd;

    @JsonProperty("OrganisationToRemove")
    private Organisation organisationToRemove;

    @JsonProperty("ApprovalStatus")
    private ChangeOrganisationApprovalStatus approvalStatus;

    @JsonProperty("CreatedBy")
    private String createdBy;
}
