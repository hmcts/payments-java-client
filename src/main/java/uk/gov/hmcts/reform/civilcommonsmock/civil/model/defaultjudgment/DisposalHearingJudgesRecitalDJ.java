package uk.gov.hmcts.reform.civilcommonsmock.civil.model.defaultjudgment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DisposalHearingJudgesRecitalDJ {

    private String judgeNameTitle;

    private String input;

}
