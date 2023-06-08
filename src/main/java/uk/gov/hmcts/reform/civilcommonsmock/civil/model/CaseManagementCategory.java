package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.common.Element;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CaseManagementCategory {

    private CaseManagementCategoryElement value;
    @SuppressWarnings("checkstyle:MemberName")
    private List<Element<CaseManagementCategoryElement>> list_items;
}