package uk.gov.hmcts.reform.civilcommonsmock.civil.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uk.gov.hmcts.reform.civilcommonsmock.civil.documentmanagement.model.Document;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.CoSRecipientServeLocationOwnerType;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.CoSRecipientServeType;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.CosRecipientServeLocationType;
import uk.gov.hmcts.reform.civilcommonsmock.civil.enums.YesOrNo;
import uk.gov.hmcts.reform.civilcommonsmock.civil.model.common.Element;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CertificateOfService {

    @JsonProperty("cosDateOfServiceForDefendant")
    private LocalDate cosDateOfServiceForDefendant;
    @JsonProperty("cosServedDocumentFiles")
    private String cosServedDocumentFiles;
    @JsonProperty("cosEvidenceDocument")
    List<Element<Document>> cosEvidenceDocument;
    @JsonProperty("cosRecipient")
    private String cosRecipient;
    @JsonProperty("cosRecipientServeType")
    private CoSRecipientServeType cosRecipientServeType;
    @JsonProperty("cosRecipientServeLocation")
    private String cosRecipientServeLocation;
    @JsonProperty("cosRecipientServeLocationOwnerType")
    private CoSRecipientServeLocationOwnerType cosRecipientServeLocationOwnerType;
    @JsonProperty("cosRecipientServeLocationType")
    private CosRecipientServeLocationType cosRecipientServeLocationType;
    @JsonProperty("cosRecipientServeLocationTypeOther")
    private String cosRecipientServeLocationTypeOther;
    @JsonProperty("cosSender")
    private String cosSender;
    @JsonProperty("cosSenderFirm")
    private String cosSenderFirm;
    @JsonProperty("cosSenderStatementOfTruthLabel")
    private List<String> cosSenderStatementOfTruthLabel;
    @JsonProperty("cosDocSaved")
    private YesOrNo cosDocSaved;
}
