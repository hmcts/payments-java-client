package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;

@Data
@Builder
public class PartnerAndDependentsLRspec {

    private final YesOrNo liveWithPartnerRequired;
    private final YesOrNo partnerAgedOver;
    private final YesOrNo haveAnyChildrenRequired;
    private final YesOrNo receiveDisabilityPayments;
    private final ChildrenByAgeGroupLRspec howManyChildrenByAgeGroup;
    private final YesOrNo supportedAnyoneFinancialRequired;
    private final String supportPeopleNumber;
    private final String supportPeopleDetails;
}
