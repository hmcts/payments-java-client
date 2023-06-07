package uk.gov.hmcts.reform.civilcommonsmock.civil.model.dq;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.dq.Language;

@Data
@Builder
public class WelshLanguageRequirements {

    private final Language evidence;
    private final Language court;
    private final Language documents;
}
