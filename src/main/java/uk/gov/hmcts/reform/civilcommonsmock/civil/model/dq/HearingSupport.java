package uk.gov.hmcts.reform.civilcommonsmock.civil.model.dq;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.dq.SupportRequirements;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class HearingSupport {

    // CIV-5557 to be removed
    private final List<SupportRequirements> requirements;
    private final String signLanguageRequired;
    private final String languageToBeInterpreted;
    private final String otherSupport;
    private final YesOrNo supportRequirements;
    private final String supportRequirementsAdditional;
}
