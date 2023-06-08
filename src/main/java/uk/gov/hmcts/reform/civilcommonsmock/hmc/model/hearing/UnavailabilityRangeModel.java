package uk.gov.hmcts.reform.civilcommonsmock.hmc.model.hearing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnavailabilityRangeModel {

    private String unavailableFromDate;
    private String unavailableToDate;
    private UnavailabilityType unavailabilityType;
}
