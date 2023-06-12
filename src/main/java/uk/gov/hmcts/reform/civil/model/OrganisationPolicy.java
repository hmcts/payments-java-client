package uk.gov.hmcts.reform.civil.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.ccd.model.Organisation;
import uk.gov.hmcts.reform.ccd.model.PreviousOrganisationCollectionItem;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganisationPolicy {

    @JsonProperty("Organisation")
    private Organisation organisation;

    @JsonProperty("OrgPolicyReference")
    private String orgPolicyReference;

    @JsonProperty("OrgPolicyCaseAssignedRole")
    private String orgPolicyCaseAssignedRole;

    @JsonProperty("PreviousOrganisations")
    private List<PreviousOrganisationCollectionItem> previousOrganisations;

}
