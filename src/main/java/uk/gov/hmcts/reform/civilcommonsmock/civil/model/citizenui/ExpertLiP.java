package uk.gov.hmcts.reform.civilcommonsmock.civil.model.citizenui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.common.Element;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertLiP {

    private YesOrNo caseNeedsAnExpert;
    private String expertCanStillExamineDetails;
    private YesOrNo expertReportRequired;
    private List<Element<ExpertReportLiP>> details;
}
