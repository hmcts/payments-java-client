package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnemployedComplexTypeLRspec {

    private final String unemployedComplexTypeRequired;
    private final LengthOfUnemploymentComplexTypeLRspec lengthOfUnemployment;
    private final String otherUnemployment;
}
