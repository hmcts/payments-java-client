package uk.gov.hmcts.reform.payments.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardPaymentServiceRequestResponse {

    @JsonProperty("external_reference")
    private String externalReference;

    @JsonProperty("payment_reference")
    private String paymentReference;

    @JsonProperty("status")
    private String status;

    @JsonProperty("next_url")
    private String nextUrl;

    @JsonProperty("date_created")
    private String dateCreated;
}
