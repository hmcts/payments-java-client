package uk.gov.hmcts.reform.payments.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemissionDto {

    @JsonProperty("add_refund")
    private boolean addRefund;

    @JsonProperty("beneficiary_name")
    private String beneficiaryName;

    @JsonProperty("case_reference")
    private String caseReference;

    @JsonProperty("ccd_case_number")
    private String ccdCaseNumber;

    @JsonProperty("date_created")
    private String dateCreated;

    @JsonProperty("fee_code")
    private String feeCode;

    @JsonProperty("fee_id")
    private String feeId;

    @JsonProperty("hwf_amount")
    private BigDecimal hwfAmount;

    @JsonProperty("hwf_reference")
    private String hwfReference;

    private String id;

    @JsonProperty("issue_refund_add_refund_add_remission")
    private boolean issueRefundAddRefundAddRemission;

    @JsonProperty("payment_group_reference")
    private String paymentGroupReference;

    @JsonProperty("payment_reference")
    private String paymentReference;

    @JsonProperty("remission_reference")
    private String remissionReference;

}
