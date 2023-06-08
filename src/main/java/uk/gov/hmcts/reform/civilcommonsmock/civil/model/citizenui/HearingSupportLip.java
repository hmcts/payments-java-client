package uk.gov.hmcts.reform.civilcommonsmock.civil.model.citizenui;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.common.Element;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.dq.RequirementsLip;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class HearingSupportLip {

    private final YesOrNo supportRequirementLip;
    private final List<Element<RequirementsLip>> requirementsLip;

}
