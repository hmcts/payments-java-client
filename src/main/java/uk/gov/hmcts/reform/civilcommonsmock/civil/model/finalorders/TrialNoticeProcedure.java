package uk.gov.hmcts.reform.civilcommonsmock.civil.model.finalorders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.finalorders.FinalOrdersClaimantDefendantNotAttending;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TrialNoticeProcedure {

    private FinalOrdersClaimantDefendantNotAttending list;
    private FinalOrdersClaimantDefendantNotAttending listDef;
}
