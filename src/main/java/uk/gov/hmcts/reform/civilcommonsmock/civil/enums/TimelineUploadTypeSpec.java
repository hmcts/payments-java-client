package uk.gov.hmcts.reform.civilcommonsmock.civil.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TimelineUploadTypeSpec {
    UPLOAD("Upload"),
    MANUAL("Manual");

    private final String displayedValue;
}
