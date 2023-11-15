package uk.gov.hmcts.reform.payments.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentGroupDto {

    @JsonProperty("any_payment_disputed")
    private boolean anyPaymentDisputed;
    @JsonProperty("date_created")
    private OffsetDateTime dateCreated;
    @JsonProperty("date_updated")
    private OffsetDateTime dateUpdated;
    private List<FeeDto> fees;
    @JsonProperty("payment_group_reference")
    private String paymentGroupReference;
    private List<PaymentDto> payments;
    private List<RemissionDto> remissions;
    @JsonProperty("service_request_status")
    private String serviceRequestStatus;
}
