package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.PartyRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class PartyData {

    private final PartyRole role;
    private final Party details;
    private final LocalDateTime timeExtensionDate;
    public final LocalDate solicitorAgreedDeadlineExtension;
}
