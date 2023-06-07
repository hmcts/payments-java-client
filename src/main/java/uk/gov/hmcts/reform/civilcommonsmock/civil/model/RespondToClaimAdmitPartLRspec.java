package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.common.MappableObject;
import uk.gov.hmcts.reform.civilcommonsmock.civil.validation.groups.PaymentDateGroup;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class RespondToClaimAdmitPartLRspec implements MappableObject {

    @FutureOrPresent(message = "Date for when will the amount be paid must be today or in the future.",
            groups = PaymentDateGroup.class)
    private final LocalDate whenWillThisAmountBePaid;

    @JsonCreator
    public RespondToClaimAdmitPartLRspec(@JsonProperty("whenWillThisAmountBePaid") LocalDate whenWillThisAmountBePaid) {
        this.whenWillThisAmountBePaid = whenWillThisAmountBePaid;
    }
}
