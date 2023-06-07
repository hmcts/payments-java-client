package uk.gov.hmcts.reform.civilcommonsmock.civil.model.defaultjudgment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.dj.DisposalHearingBundleType;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DisposalHearingBundleDJ {

    private String input;
    private DisposalHearingBundleType type;
}
