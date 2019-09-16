package uk.gov.hmcts.reform.payments.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistoryDto {
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateUpdated;
    private String errorCode;
    private String errorMessage;
    private String externalStatus;
    private String status;
}
