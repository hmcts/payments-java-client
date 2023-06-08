package uk.gov.hmcts.reform.civilcommonsmock.civil.model.account;

import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AccountSimple {

    /**
     * balance in pounds.
     */
    @NotNull
    private BigDecimal balance;
    /**
     * true if joint account, false if not, null if unknown.
     */
    @NotNull
    private YesOrNo jointAccount;
    @NotNull
    private AccountType accountType;
}