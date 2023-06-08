package uk.gov.hmcts.reform.civilcommonsmock.civil.model.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.civilcommonsmock.civil.documentmanagement.model.Document;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DocumentAndNote {

    private String documentName;
    private Document document;
    private String documentNote;
    @Builder.Default
    private LocalDateTime createdDateTime = LocalDateTime.now();

}
