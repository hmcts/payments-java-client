package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class PayingMoneyDetails {

    private final String claimNumberText;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final BigDecimal amountOwed;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final BigDecimal monthlyInstalmentAmount;

}
