package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.civilcommonsmock.civil.documentmanagement.model.Document;

@Data
@Builder
public class ResponseDocument {

    private final Document file;

    @JsonCreator
    public ResponseDocument(Document file) {
        this.file = file;
    }
}
