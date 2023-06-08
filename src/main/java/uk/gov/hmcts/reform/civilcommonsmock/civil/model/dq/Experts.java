package uk.gov.hmcts.reform.civilcommonsmock.civil.model.dq;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.ExpertReportsSent;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.common.Element;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Experts {

    private final YesOrNo expertRequired;
    private final ExpertReportsSent expertReportsSent;
    private final YesOrNo jointExpertSuitable;
    private final List<Element<Expert>> details;
}
