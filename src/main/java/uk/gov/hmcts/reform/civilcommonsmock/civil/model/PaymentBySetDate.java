package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PaymentBySetDate {

    private LocalDate paymentSetDate;

    @JsonCreator
    public PaymentBySetDate(@JsonProperty("paymentSetDate") LocalDate paymentSetDate) {
        this.paymentSetDate = paymentSetDate;
    }
}
