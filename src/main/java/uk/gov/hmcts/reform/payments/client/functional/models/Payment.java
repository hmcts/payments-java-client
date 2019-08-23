package uk.gov.hmcts.reform.payments.client.functional.models;

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

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private String description;
    private String currency;
    private String ccdCaseNumber;
    private String caseReference;
    private String channel;
    private String method;
    private String externalProvider;
    private String externalReference;
    private String siteId;
    private String serviceName;
    private Fee[] fees;
    private String reference;
    private String dateCreated;
    private String status;
    private String paymentGroupReference;
    private BigDecimal amount;
    @JsonProperty("_links") // snake case strategy is meant to handle this, but doesn't.
    private NavigationLinks links;
}
