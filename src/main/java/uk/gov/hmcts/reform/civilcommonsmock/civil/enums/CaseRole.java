package uk.gov.hmcts.reform.civilcommonsmock.civil.enums;

import lombok.Getter;

@Getter
public enum CaseRole {
    CREATOR,
    APPLICANTSOLICITORONE,
    RESPONDENTSOLICITORONE,
    RESPONDENTSOLICITORTWO;

    private final String formattedName;

    CaseRole() {
        this.formattedName = String.format("[%s]", name());
    }
}
