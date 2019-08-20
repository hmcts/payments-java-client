package uk.gov.hmcts.reform.payments.client.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Fee {
    private BigDecimal calculatedAmount;
    private String code;
    private String version;
    private int id;
    private int volume;
    private String ccdCaseNumber;
}
