package uk.gov.hmcts.reform.payments.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    @JsonProperty("_links")
    private LinksDto links;
    private String accountNumber;
    private BigDecimal amount;
    private String caseReference;
    private String ccdCaseNumber;
    private String channel;
    private String currency = "GBP";
    private String customerReference;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateUpdated;
    private String description;
    private String externalProvider;
    private String externalReference;
    private FeeDto[] fees;
    private String giroSlipNo;
    private String id;
    private String method;
    private String organisationName;
    private String paymentGroupReference;
    private String paymentReference;
    private String reference;
    private String reportedDateOffline;
    // at the time of writing, permitted services are CMC, DIVORCE, PROBATE, FINREM and DIGITAL_BAR
    private String serviceName;
    private String siteId;
    private String status;
    private StatusHistoryDto[] statusHistories;
}
