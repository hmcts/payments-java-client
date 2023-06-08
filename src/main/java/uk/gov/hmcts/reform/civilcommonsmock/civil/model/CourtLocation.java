package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.common.DynamicList;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.defaultjudgment.CaseLocationCivil;

@Data
@Builder(toBuilder = true)
public class CourtLocation {

    private final String applicantPreferredCourt;
    private final DynamicList applicantPreferredCourtLocationList;
    private final CaseLocationCivil caseLocation;

    @JsonCreator
    CourtLocation(@JsonProperty("applicantPreferredCourt") String applicantPreferredCourt,
                  @JsonProperty("applicantPreferredCourtLocationList") DynamicList applicantPreferredCourtLocationList,
                  @JsonProperty("caseLocation") CaseLocationCivil caseLocation) {
        this.applicantPreferredCourt = applicantPreferredCourt;
        this.applicantPreferredCourtLocationList = applicantPreferredCourtLocationList;
        this.caseLocation = caseLocation;
    }
}
