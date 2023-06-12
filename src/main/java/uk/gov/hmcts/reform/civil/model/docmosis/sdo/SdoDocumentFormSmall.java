package uk.gov.hmcts.reform.civil.model.docmosis.sdo;

import lombok.*;
import uk.gov.hmcts.reform.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civil.model.Party;
import uk.gov.hmcts.reform.civil.enums.sdo.ClaimsTrack;
import uk.gov.hmcts.reform.civil.enums.sdo.SmallClaimsMethod;
import uk.gov.hmcts.reform.civil.enums.sdo.SmallTrack;
import uk.gov.hmcts.reform.civil.model.common.DynamicList;
import uk.gov.hmcts.reform.civil.model.common.Element;
import uk.gov.hmcts.reform.civil.model.common.MappableObject;
import uk.gov.hmcts.reform.civil.model.sdo.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SdoDocumentFormSmall implements MappableObject {

    private final LocalDate currentDate;

    private final String judgeName;

    private final String caseNumber;

    private final Party applicant1;
    private final Party respondent1;
    private final boolean hasApplicant2;
    private final Party applicant2;
    private final boolean hasRespondent2;
    private final Party respondent2;

    private final YesOrNo drawDirectionsOrderRequired;
    private final JudgementSum drawDirectionsOrder;
    private final ClaimsTrack claimsTrack;

    private final List<SmallTrack> smallClaims;

    private final boolean hasCreditHire;
    private final boolean hasRoadTrafficAccident;

    private final SmallClaimsJudgesRecital smallClaimsJudgesRecital;
    private final SmallClaimsHearing smallClaimsHearing;
    private final String smallClaimsHearingTime;
    private final SmallClaimsMethod smallClaimsMethod;
    private final DynamicList smallClaimsMethodInPerson;
    private final String smallClaimsMethodTelephoneHearing;
    private final String smallClaimsMethodVideoConferenceHearing;
    private final SmallClaimsDocuments smallClaimsDocuments;
    private final SmallClaimsWitnessStatement smallClaimsWitnessStatement;

    private final SmallClaimsCreditHire smallClaimsCreditHire;
    private final SmallClaimsRoadTrafficAccident smallClaimsRoadTrafficAccident;

    private final boolean hasNewDirections;
    private final List<Element<SmallClaimsAddNewDirections>> smallClaimsAddNewDirections;

    private final SmallClaimsNotes smallClaimsNotes;

    private final boolean smallClaimsHearingToggle;
    private final boolean smallClaimsMethodToggle;
    private final boolean smallClaimsDocumentsToggle;
    private final boolean smallClaimsWitnessStatementToggle;
}
