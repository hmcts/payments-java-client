package uk.gov.hmcts.reform.civilcommonsmock.civil.model.sdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.sdo.TrialHearingTimeEstimateDJ;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TrialHearingTimeDJ {

    private LocalDate date1;
    private LocalDate date2;
    private TrialHearingTimeEstimateDJ hearingTimeEstimate;
    private String helpText1;
    private String helpText2;
}