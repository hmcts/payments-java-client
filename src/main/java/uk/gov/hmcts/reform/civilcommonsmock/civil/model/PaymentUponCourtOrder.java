package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.common.Element;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PaymentUponCourtOrder {

    private final YesOrNo payingDetailsRequired;
    private final List<Element<PayingMoneyDetails>> payingMoneyDetails;

}




