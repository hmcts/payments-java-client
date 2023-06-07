package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChildrenByAgeGroupLRspec {

    private final String numberOfUnderEleven;
    private final String numberOfElevenToFifteen;
    private final String numberOfSixteenToNineteen;
}
