package uk.gov.hmcts.reform.civilcommonsmock.civil.model.docmosis.dq;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.dq.Witness;

import java.util.List;

@Data
@Builder
public class Witnesses {

    private final YesOrNo witnessesToAppear;
    private final List<Witness> details;
}
