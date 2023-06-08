package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CaseNote {

    private final String createdBy;
    private final LocalDateTime createdOn;
    private final String note;
}
