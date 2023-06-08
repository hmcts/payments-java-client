package uk.gov.hmcts.reform.civilcommonsmock.civil.model.citizenui;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DashboardClaimStatusFactory {

    public DashboardClaimStatus getDashboardClaimStatus(Claim claim) {
        return Arrays.stream(DashboardClaimStatus.values())
                .filter(status -> status.getClaimMatcher().test(claim))
                .findFirst()
                .orElse(DashboardClaimStatus.NO_STATUS);
    }

}
