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
public class SmallClaimsCreditHire {

    private String input1;
    private String input2;
    private String input3;
    private String input4;
    private String input5;
    private String input6;
    private String input7;
    private String input11;
    @Future(message = "The date entered must be in the future")
    private LocalDate date1;
    @Future(message = "The date entered must be in the future")
    private LocalDate date2;
    @Future(message = "The date entered must be in the future")
    private LocalDate date3;
    @Future(message = "The date entered must be in the future")
    private LocalDate date4;
}