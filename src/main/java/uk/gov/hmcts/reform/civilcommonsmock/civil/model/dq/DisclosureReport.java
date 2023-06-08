package uk.gov.hmcts.reform.civilcommonsmock.civil.model.dq;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;

@Data
@Builder
public class DisclosureReport {

    private final YesOrNo disclosureFormFiledAndServed;
    private final YesOrNo disclosureProposalAgreed;
    private final String draftOrderNumber;
}
