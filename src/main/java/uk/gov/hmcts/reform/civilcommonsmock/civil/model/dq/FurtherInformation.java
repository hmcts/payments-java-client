package uk.gov.hmcts.reform.civilcommonsmock.civil.model.dq;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;

@Data
@Builder(toBuilder = true)
public class FurtherInformation {

    private final YesOrNo futureApplications;
    private final YesOrNo intentionToMakeFutureApplications;
    private final String reasonForFutureApplications;
    private final String otherInformationForJudge;
}
