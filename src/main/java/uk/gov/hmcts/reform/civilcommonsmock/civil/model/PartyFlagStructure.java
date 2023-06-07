package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.caseflags.Flags;

@Data
@Builder(toBuilder = true)
public class PartyFlagStructure {

    private final String partyID;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final Flags flags;
}
