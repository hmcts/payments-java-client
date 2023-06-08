package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.validation.groups.PaymentDateGroup;

import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class RespondToClaim {

    /**
     * money amount in pence.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final BigDecimal howMuchWasPaid;
    @PastOrPresent(message = "Date for when amount was paid must be today or in the past",
            groups = PaymentDateGroup.class)
    private final LocalDate whenWasThisAmountPaid;
    private final PaymentMethod howWasThisAmountPaid;
    private final String howWasThisAmountPaidOther;
}
