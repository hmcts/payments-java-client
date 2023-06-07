package uk.gov.hmcts.reform.civilcommonsmock.civil.model.defaultjudgment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.dj.DisposalHearingFinalDisposalHearingTimeEstimate;

import javax.validation.constraints.Future;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DisposalHearingFinalDisposalHearingDJ {

    private String input;
    @Future(message = "The date entered must be in the future")
    private LocalDate date;
    private DisposalHearingFinalDisposalHearingTimeEstimate time;

}
