package uk.gov.hmcts.reform.civilcommonsmock.civil.model.sdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.sdo.DisposalHearingFinalDisposalHearingTimeEstimate;

import javax.validation.constraints.Future;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DisposalHearingHearingTime {

    private String input;
    private LocalDate dateFrom;
    @Future(message = "The date entered must be in the future")
    private LocalDate dateTo;
    private DisposalHearingFinalDisposalHearingTimeEstimate time;
}
