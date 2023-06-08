package uk.gov.hmcts.reform.civilcommonsmock.civil.model.dq;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.UnavailableDate;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.common.Element;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class SmallClaimHearing {

    private final YesOrNo unavailableDatesRequired;
    private final List<Element<UnavailableDate>> smallClaimUnavailableDate;

}
