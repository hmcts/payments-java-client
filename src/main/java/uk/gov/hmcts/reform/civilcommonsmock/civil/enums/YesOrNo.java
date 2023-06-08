package uk.gov.hmcts.reform.civilcommonsmock.civil.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum YesOrNo {
    @JsonProperty("Yes")
    YES,
    @JsonProperty("No")
    NO
}
