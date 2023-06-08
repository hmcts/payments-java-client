package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.ReasonForProceedingOnPaper;
import uk.gov.hmcts.reform.civilcommonsmock.civil.validation.groups.CasemanTransferDateGroup;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Builder
public class ClaimProceedsInCasemanLR {

    @PastOrPresent(message = "The date entered cannot be in the future", groups = CasemanTransferDateGroup.class)
    private LocalDate date;
    private ReasonForProceedingOnPaper reason;
    private String other;
}
