package uk.gov.hmcts.reform.civilcommonsmock.civil.model.docmosis.dq;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.UnavailableDate;

import java.util.List;

@Data
@Builder
public class Hearing {

    private final String hearingLength;
    private final YesOrNo unavailableDatesRequired;
    private final List<UnavailableDate> unavailableDates;

}
