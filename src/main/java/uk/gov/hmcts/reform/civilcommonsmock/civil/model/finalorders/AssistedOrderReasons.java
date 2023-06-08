package uk.gov.hmcts.reform.civilcommonsmock.civil.model.finalorders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AssistedOrderReasons {

    private String reasonsText;
}
