package uk.gov.hmcts.reform.civilcommonsmock.civil.model.genapplication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;

import java.time.LocalDate;

@Setter
@Data
@Builder(toBuilder = true)
public class GAHearingDateGAspec {

    private final YesOrNo hearingScheduledPreferenceYesNo;
    private final LocalDate hearingScheduledDate;

    @JsonCreator
    GAHearingDateGAspec(@JsonProperty("hearingScheduledPreferenceYesNo") YesOrNo hearingScheduledPreferenceYesNo,
                        @JsonProperty("hearingScheduledDate") LocalDate hearingScheduledDate) {

        this.hearingScheduledPreferenceYesNo = hearingScheduledPreferenceYesNo;
        this.hearingScheduledDate = hearingScheduledDate;
    }
}
