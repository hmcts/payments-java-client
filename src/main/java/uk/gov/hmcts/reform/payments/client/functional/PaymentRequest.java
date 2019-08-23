package uk.gov.hmcts.reform.payments.client.functional;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.payments.client.functional.models.Fee;

import java.math.BigDecimal;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaymentRequest {
    private String caseReference;
    private String ccdCaseNumber;
    private String description;
    private String service;
    private String currency;
    private String siteId;
    private Fee[] fees;
    private BigDecimal amount; // in pounds
}
