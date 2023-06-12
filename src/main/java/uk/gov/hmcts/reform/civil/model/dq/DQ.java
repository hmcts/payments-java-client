package uk.gov.hmcts.reform.civil.model.dq;

import uk.gov.hmcts.reform.civil.documentmanagement.model.Document;
import uk.gov.hmcts.reform.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civil.model.StatementOfTruth;

public interface DQ {

    FileDirectionsQuestionnaire getFileDirectionQuestionnaire();

    DisclosureOfElectronicDocuments getDisclosureOfElectronicDocuments();

    DisclosureOfNonElectronicDocuments getDisclosureOfNonElectronicDocuments();

    DisclosureReport getDisclosureReport();

    ExpertDetails getSmallClaimExperts();

    Experts getExperts();

    default Experts getExperts(Experts experts) {
        if (experts != null && experts.getExpertRequired() != null
                && experts.getExpertRequired().equals(YesOrNo.NO)) {
            return experts.toBuilder().details(null).build();
        }
        return experts;
    }

    Witnesses getWitnesses();

    default Witnesses getWitnesses(Witnesses witnesses) {
        if (witnesses != null && witnesses.getWitnessesToAppear() != null
                && witnesses.getWitnessesToAppear().equals(YesOrNo.NO)) {
            return witnesses.toBuilder().details(null).build();
        }
        return witnesses;
    }

    Hearing getHearing();

    default Hearing getHearing(Hearing hearing) {
        if (hearing != null && hearing.getUnavailableDatesRequired() != null
                && hearing.getUnavailableDatesRequired().equals(YesOrNo.NO)) {
            return hearing.toBuilder().unavailableDates(null).build();
        }
        return hearing;
    }

    SmallClaimHearing getSmallClaimHearing();

    default SmallClaimHearing getSmallClaimHearing(SmallClaimHearing smallClaimHearing) {
        if (smallClaimHearing != null && smallClaimHearing.getUnavailableDatesRequired() != null
                && smallClaimHearing.getUnavailableDatesRequired().equals(YesOrNo.NO)) {
            return smallClaimHearing.toBuilder().smallClaimUnavailableDate(null).build();
        }
        return smallClaimHearing;
    }

    Document getDraftDirections();

    RequestedCourt getRequestedCourt();

    HearingSupport getHearingSupport();

    FurtherInformation getFurtherInformation();

    WelshLanguageRequirements getWelshLanguageRequirements();

    WelshLanguageRequirements getWelshLanguageRequirementsLRspec();

    StatementOfTruth getStatementOfTruth();

    VulnerabilityQuestions getVulnerabilityQuestions();
}
