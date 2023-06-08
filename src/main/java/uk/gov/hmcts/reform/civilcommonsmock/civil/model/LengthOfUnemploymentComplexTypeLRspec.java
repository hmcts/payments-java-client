package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LengthOfUnemploymentComplexTypeLRspec {

    private final String numberOfYearsInUnemployment;
    private final String numberOfMonthsInUnemployment;
}
