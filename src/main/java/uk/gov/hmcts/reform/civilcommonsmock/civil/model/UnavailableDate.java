package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.dq.UnavailableDateType;
import uk.gov.hmcts.reform.civilcommonsmock.civil.validation.groups.UnavailableDateGroup;
import uk.gov.hmcts.reform.civilcommonsmock.civil.validation.interfaces.IsPresentOrEqualToOrLessThanOneYearInTheFuture;

import java.time.LocalDate;

@Data
@Builder
@IsPresentOrEqualToOrLessThanOneYearInTheFuture(groups = UnavailableDateGroup.class)
public class UnavailableDate {

    private final String who;
    private final LocalDate date;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final UnavailableDateType unavailableDateType;
}
