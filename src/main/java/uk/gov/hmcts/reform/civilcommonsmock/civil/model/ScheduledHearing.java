package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ScheduledHearing {

    private final String hearingID;
    private final LocalDateTime hearingDateTime;
}
