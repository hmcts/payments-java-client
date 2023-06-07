package uk.gov.hmcts.reform.civilcommonsmock.civil.enums.dj;

import lombok.Getter;

@Getter
public enum HearingMethodVideoConferenceDJ {
    videoTheClaimant("the claimant"),
    videoTheDefendant("the defendant"),
    videoTheCourt("the court");

    private final String label;

    HearingMethodVideoConferenceDJ(String value) {
        this.label = value;
    }

}

