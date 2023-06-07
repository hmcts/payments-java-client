package uk.gov.hmcts.reform.civilcommonsmock.civil.model.genapplication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaseLink {

    @JsonProperty(value = "CaseReference")
    private String caseReference;

}
