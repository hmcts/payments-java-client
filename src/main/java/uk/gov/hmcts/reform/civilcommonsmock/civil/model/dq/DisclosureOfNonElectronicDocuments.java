package uk.gov.hmcts.reform.civilcommonsmock.civil.model.dq;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;

@Data
@Builder
public class DisclosureOfNonElectronicDocuments {

    private final YesOrNo directionsForDisclosureProposed;
    private final YesOrNo standardDirectionsRequired;
    private final String bespokeDirections;
}
