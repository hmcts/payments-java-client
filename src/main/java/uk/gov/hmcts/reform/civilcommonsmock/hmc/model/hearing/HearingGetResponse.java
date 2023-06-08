package uk.gov.hmcts.reform.civilcommonsmock.hmc.model.hearing;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HearingGetResponse {

    @NonNull
    private HearingRequestDetails requestDetails;

    @NonNull
    private HearingDetails hearingDetails;

    @NonNull
    private CaseDetailsHearing caseDetails;

    @NonNull
    private List<PartyDetailsModel> partyDetails;

    @NonNull
    private HearingResponse hearingResponse;

}
