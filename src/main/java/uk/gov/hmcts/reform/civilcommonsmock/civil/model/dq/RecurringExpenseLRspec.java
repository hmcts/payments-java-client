package uk.gov.hmcts.reform.civilcommonsmock.civil.model.dq;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.PaymentFrequencyLRspec;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.dq.ExpenseTypeLRspec;

import java.math.BigDecimal;

@Data
public class RecurringExpenseLRspec {

    private final ExpenseTypeLRspec type;
    private final String typeOtherDetails;
    /**
     * amount in pence.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final BigDecimal amount;
    private final PaymentFrequencyLRspec frequency;
}
