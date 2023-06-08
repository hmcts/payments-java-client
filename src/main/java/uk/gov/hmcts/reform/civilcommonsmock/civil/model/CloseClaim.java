package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.validation.groups.ClaimWithdrawalDateGroup;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Builder
public class CloseClaim {

    @PastOrPresent(message = "The date must not be in the future", groups = ClaimWithdrawalDateGroup.class)
    private LocalDate date;
    private String reason;
}