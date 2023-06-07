package uk.gov.hmcts.reform.civilcommonsmock.civil.model.hearingvalues;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.hearing.MemberType;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.hearing.RequirementType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PanelPreferenceModel {

    private String memberID;
    private MemberType memberType;
    private RequirementType requirementType;
}
