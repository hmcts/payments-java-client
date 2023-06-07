package uk.gov.hmcts.reform.civilcommonsmock.civil.documentmanagement;

import uk.gov.hmcts.reform.civilcommonsmock.civil.documentmanagement.model.CaseDocument;
import uk.gov.hmcts.reform.civilcommonsmock.civil.documentmanagement.model.PDF;

public interface DocumentManagementService {

    CaseDocument uploadDocument(String authorisation, PDF pdf);

    byte[] downloadDocument(String authorisation, String documentPath);

}
