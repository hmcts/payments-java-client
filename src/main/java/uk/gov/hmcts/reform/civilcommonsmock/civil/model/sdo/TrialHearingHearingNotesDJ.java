package uk.gov.hmcts.reform.civilcommonsmock.civil.model.sdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TrialHearingHearingNotesDJ {

    private String input;
}