package uk.gov.hmcts.reform.civilcommonsmock.civil.model.sdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class FastTrackSchedulesOfLoss {

    private String input1;
    @Future(message = "The date entered must be in the future")
    private LocalDate date1;
    private String input2;
    @Future(message = "The date entered must be in the future")
    private LocalDate date2;
    private String input3;
}