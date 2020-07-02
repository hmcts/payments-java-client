package uk.gov.hmcts.reform.payments.client.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.gov.hmcts.reform.payments.client.models.FeeDto;
import uk.gov.hmcts.reform.payments.client.models.PaymentDto;
import uk.gov.hmcts.reform.payments.client.request.CardPaymentRequest;

import java.math.BigDecimal;
import java.util.UUID;

import static java.math.BigDecimal.ROUND_UNNECESSARY;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@EnableAutoConfiguration
@DisplayName("Create payment API")
class CreatePaymentTest extends BaseTest {
    private static final BigDecimal TEN_2_DP = BigDecimal.TEN.setScale(2, ROUND_UNNECESSARY);

    private static final String CCD_CASE_NUMBER = "UNKNOWN";
    private static final String CURRENCY = "GBP";
    private static final String EXTERNAL_PROVIDER = "gov pay";
    private static final BigDecimal FEE_AMOUNT = TEN_2_DP;
    private static final String FEE_CODE = "FEE0234";
    private static final String FEE_DESCRIPTION = "A sample fee";
    private static final String FEE_REFERENCE = "reference";
    private static final String FEE_VERSION = "version";
    private static final Integer FEE_VOLUME = 1;
    private static final String INITIATED_STATUS = "Initiated";
    private static final String FAILED_STATUS = "Failed";
    private static final String JURISDICTION_1 = "jurisdiction 1";
    private static final String JURISDICTION_2 = "jurisdiction 2";
    private static final String MEMO_LINE = "Memo line";
    private static final String NATURAL_ACCOUNT_CODE = "natural account code";
    private static final BigDecimal NET_FEE_AMOUNT = TEN_2_DP;
    private static final String ONLINE_CHANNEL = "online";
    private static final BigDecimal PAYMENT_AMOUNT = TEN_2_DP;
    private static final String PAYMENT_DESCRIPTION = "description";
    private static final String PAYMENT_METHOD = "card";
    private static final String SERVICE = "CMC";
    private static final String SERVICE_NAME = "Civil Money Claims";
    private static final String SITE_ID = "AA00";

    private static final CardPaymentRequest CARD_PAYMENT_REQUEST = CardPaymentRequest.builder()
        .caseReference(UUID.randomUUID().toString())
        .ccdCaseNumber(CCD_CASE_NUMBER)
        .description(PAYMENT_DESCRIPTION)
        .service(SERVICE)
        .currency(CURRENCY)
        .siteId(SITE_ID)
        .fees(new FeeDto[]{
            FeeDto.builder()
                .id(1)
                .calculatedAmount(FEE_AMOUNT)
                .ccdCaseNumber(CCD_CASE_NUMBER)
                .code(FEE_CODE)
                .description(FEE_DESCRIPTION)
                .jurisdiction1(JURISDICTION_1)
                .jurisdiction2(JURISDICTION_2)
                .memoLine(MEMO_LINE)
                .naturalAccountCode(NATURAL_ACCOUNT_CODE)
                .netAmount(NET_FEE_AMOUNT)
                .reference(FEE_REFERENCE)
                .version(FEE_VERSION)
                .volume(FEE_VOLUME)
                .build()
        })
        .amount(PAYMENT_AMOUNT)
        .build();

    @Test
    void canCreateRetrieveAndCancelPayments() {
        User citizen = createCitizen();
        PaymentDto createdPayment = paymentsClient.createCardPayment(
            citizen.getAuthToken(),
            CARD_PAYMENT_REQUEST,
            "https://www.google.com"
        );
        verifyCreatedPayment(createdPayment);

        final String paymentGroupReference = createdPayment.getPaymentGroupReference();
        final String reference = createdPayment.getReference();

        PaymentDto retrievedPayment = paymentsClient.retrieveCardPayment(
            citizen.getAuthToken(),
            reference
        );
        verifyRetrievedPayment(retrievedPayment, paymentGroupReference, reference);

        paymentsClient.cancelCardPayment(citizen.getAuthToken(), reference);

        PaymentDto cancelledPayment = paymentsClient.retrieveCardPayment(
            citizen.getAuthToken(),
            reference
        );
        verifyCancelledPayment(cancelledPayment, paymentGroupReference, reference);
    }

    private PaymentDto verifyCreatedPayment(PaymentDto createdPayment) {
        assertNotNull(createdPayment);
        assertAll("created payment",
            () -> assertAll("links",
                () -> assertNotNull(createdPayment.getLinks()),
                () -> assertNotNull(createdPayment.getLinks().getNextUrl()),
                () -> assertNotNull(createdPayment.getLinks().getNextUrl().getHref()),
                () -> assertEquals(RequestMethod.GET, createdPayment.getLinks().getNextUrl().getMethod()),
                () -> assertNull(createdPayment.getLinks().getSelf()),
                () -> assertNull(createdPayment.getLinks().getCancel())
            ),
            () -> assertAll("expected to be null",
                () -> assertNull(createdPayment.getAccountNumber()),
                () -> assertNull(createdPayment.getAmount()),
                () -> assertNull(createdPayment.getCaseReference()),
                () -> assertNull(createdPayment.getCcdCaseNumber()),
                () -> assertNull(createdPayment.getChannel()),
                () -> assertNull(createdPayment.getCustomerReference()),
                () -> assertNull(createdPayment.getDateUpdated()),
                () -> assertNull(createdPayment.getDescription()),
                () -> assertNull(createdPayment.getExternalProvider()),
                () -> assertNull(createdPayment.getFees()),
                () -> assertNull(createdPayment.getGiroSlipNo()),
                () -> assertNull(createdPayment.getId()),
                () -> assertNull(createdPayment.getMethod()),
                () -> assertNull(createdPayment.getOrganisationName()),
                () -> assertNull(createdPayment.getPaymentReference()),
                () -> assertNull(createdPayment.getReportedDateOffline()),
                () -> assertNull(createdPayment.getServiceName()),
                () -> assertNull(createdPayment.getSiteId()),
                () -> assertNull(createdPayment.getStatusHistories())
            ),
            () -> assertAll("expected known values",
                () -> assertEquals(CURRENCY, createdPayment.getCurrency()),
                () -> assertEquals(INITIATED_STATUS, createdPayment.getStatus())
            ),
            () -> assertAll("expected unknown values",
                () -> assertNotNull(createdPayment.getDateCreated()),
                () -> assertNotNull(createdPayment.getExternalReference())
            ),
            () -> assertAll("expected matching values",
                () -> assertNotNull(createdPayment.getPaymentGroupReference()),
                () -> assertNotNull(createdPayment.getReference())
            )
        );

        return createdPayment;
    }

    private void verifyRetrievedPayment(
        PaymentDto retrievedPayment,
        String expectedPaymentGroupReference,
        String expectedReference
    ) {
        assertNotNull(retrievedPayment);

        assertAll("retrieved payment",
            () -> assertAll("links",
                () -> assertNotNull(retrievedPayment.getLinks()),
                () -> assertNull(retrievedPayment.getLinks().getNextUrl()),
                () -> assertNotNull(retrievedPayment.getLinks().getSelf()),
                () -> assertNotNull(retrievedPayment.getLinks().getSelf().getHref()),
                () -> assertEquals(RequestMethod.GET, retrievedPayment.getLinks().getSelf().getMethod()),
                () -> assertNull(retrievedPayment.getLinks().getCancel())
            ),
            () -> assertAll("expected to be null",
                () -> assertNull(retrievedPayment.getAccountNumber()),
                () -> assertNull(retrievedPayment.getCustomerReference()),
                () -> assertNull(retrievedPayment.getDateCreated()),
                () -> assertNull(retrievedPayment.getDateUpdated()),
                () -> assertNull(retrievedPayment.getGiroSlipNo()),
                () -> assertNull(retrievedPayment.getId()),
                () -> assertNull(retrievedPayment.getOrganisationName()),
                () -> assertNull(retrievedPayment.getPaymentReference()),
                () -> assertNull(retrievedPayment.getReportedDateOffline()),
                () -> assertNull(retrievedPayment.getStatusHistories())
            ),
            () -> assertAll("expected known values",
                () -> assertEquals(BigDecimal.TEN, retrievedPayment.getAmount()),
                () -> assertEquals(CCD_CASE_NUMBER, retrievedPayment.getCcdCaseNumber()),
                () -> assertEquals(ONLINE_CHANNEL, retrievedPayment.getChannel()),
                () -> assertEquals(CURRENCY, retrievedPayment.getCurrency()),
                () -> assertEquals(PAYMENT_DESCRIPTION, retrievedPayment.getDescription()),
                () -> assertEquals(EXTERNAL_PROVIDER, retrievedPayment.getExternalProvider()),
                () -> assertEquals(PAYMENT_METHOD, retrievedPayment.getMethod()),
                () -> assertEquals(SERVICE_NAME, retrievedPayment.getServiceName()),
                () -> assertEquals(SITE_ID, retrievedPayment.getSiteId()),
                () -> assertEquals(INITIATED_STATUS, retrievedPayment.getStatus())
            ),
            () -> assertAll("expected matching values",
                () -> assertEquals(expectedPaymentGroupReference, retrievedPayment.getPaymentGroupReference()),
                () -> assertEquals(expectedReference, retrievedPayment.getReference())
            ),
            () -> assertAll("fees",
                () -> assertNotNull(retrievedPayment.getFees()),
                () -> assertEquals(1, retrievedPayment.getFees().length),
                () -> assertEquals(FEE_AMOUNT, retrievedPayment.getFees()[0].getCalculatedAmount()),
                () -> assertEquals(CCD_CASE_NUMBER, retrievedPayment.getFees()[0].getCcdCaseNumber()),
                () -> assertEquals(FEE_CODE, retrievedPayment.getFees()[0].getCode()),
                () -> assertNull(retrievedPayment.getFees()[0].getDescription()),
                () -> assertNotNull(retrievedPayment.getFees()[0].getId()),
                () -> assertNull(retrievedPayment.getFees()[0].getJurisdiction1()),
                () -> assertNull(retrievedPayment.getFees()[0].getJurisdiction2()),
                () -> assertNull(retrievedPayment.getFees()[0].getMemoLine()),
                () -> assertNull(retrievedPayment.getFees()[0].getNaturalAccountCode()),
                () -> assertNull(retrievedPayment.getFees()[0].getNetAmount()),
                () -> assertEquals(FEE_REFERENCE, retrievedPayment.getFees()[0].getReference()),
                () -> assertEquals(FEE_VERSION, retrievedPayment.getFees()[0].getVersion()),
                () -> assertEquals(FEE_VOLUME, retrievedPayment.getFees()[0].getVolume())
            )
        );
    }

    private void verifyCancelledPayment(
        PaymentDto retrievedPayment,
        String expectedPaymentGroupReference,
        String expectedReference
    ) {
        assertNotNull(retrievedPayment);

        assertAll("retrieved payment",
            () -> assertAll("links",
                () -> assertNotNull(retrievedPayment.getLinks()),
                () -> assertNull(retrievedPayment.getLinks().getNextUrl()),
                () -> assertNotNull(retrievedPayment.getLinks().getSelf()),
                () -> assertNotNull(retrievedPayment.getLinks().getSelf().getHref()),
                () -> assertEquals(RequestMethod.GET, retrievedPayment.getLinks().getSelf().getMethod()),
                () -> assertNull(retrievedPayment.getLinks().getCancel())
            ),
            () -> assertAll("expected to be null",
                () -> assertNull(retrievedPayment.getAccountNumber()),
                () -> assertNull(retrievedPayment.getCustomerReference()),
                () -> assertNull(retrievedPayment.getDateCreated()),
                () -> assertNull(retrievedPayment.getDateUpdated()),
                () -> assertNull(retrievedPayment.getGiroSlipNo()),
                () -> assertNull(retrievedPayment.getId()),
                () -> assertNull(retrievedPayment.getOrganisationName()),
                () -> assertNull(retrievedPayment.getPaymentReference()),
                () -> assertNull(retrievedPayment.getReportedDateOffline()),
                () -> assertNull(retrievedPayment.getStatusHistories())
            ),
            () -> assertAll("expected known values",
                () -> assertEquals(BigDecimal.TEN, retrievedPayment.getAmount()),
                () -> assertEquals(CCD_CASE_NUMBER, retrievedPayment.getCcdCaseNumber()),
                () -> assertEquals(ONLINE_CHANNEL, retrievedPayment.getChannel()),
                () -> assertEquals(CURRENCY, retrievedPayment.getCurrency()),
                () -> assertEquals(PAYMENT_DESCRIPTION, retrievedPayment.getDescription()),
                () -> assertEquals(EXTERNAL_PROVIDER, retrievedPayment.getExternalProvider()),
                () -> assertEquals(PAYMENT_METHOD, retrievedPayment.getMethod()),
                () -> assertEquals(SERVICE_NAME, retrievedPayment.getServiceName()),
                () -> assertEquals(SITE_ID, retrievedPayment.getSiteId()),
                () -> assertEquals(FAILED_STATUS, retrievedPayment.getStatus())
            ),
            () -> assertAll("expected matching values",
                () -> assertEquals(expectedPaymentGroupReference, retrievedPayment.getPaymentGroupReference()),
                () -> assertEquals(expectedReference, retrievedPayment.getReference())
            ),
            () -> assertAll("fees",
                () -> assertNotNull(retrievedPayment.getFees()),
                () -> assertEquals(1, retrievedPayment.getFees().length),
                () -> assertEquals(FEE_AMOUNT, retrievedPayment.getFees()[0].getCalculatedAmount()),
                () -> assertEquals(CCD_CASE_NUMBER, retrievedPayment.getFees()[0].getCcdCaseNumber()),
                () -> assertEquals(FEE_CODE, retrievedPayment.getFees()[0].getCode()),
                () -> assertNull(retrievedPayment.getFees()[0].getDescription()),
                () -> assertNotNull(retrievedPayment.getFees()[0].getId()),
                () -> assertNull(retrievedPayment.getFees()[0].getJurisdiction1()),
                () -> assertNull(retrievedPayment.getFees()[0].getJurisdiction2()),
                () -> assertNull(retrievedPayment.getFees()[0].getMemoLine()),
                () -> assertNull(retrievedPayment.getFees()[0].getNaturalAccountCode()),
                () -> assertNull(retrievedPayment.getFees()[0].getNetAmount()),
                () -> assertEquals(FEE_REFERENCE, retrievedPayment.getFees()[0].getReference()),
                () -> assertEquals(FEE_VERSION, retrievedPayment.getFees()[0].getVersion()),
                () -> assertEquals(FEE_VOLUME, retrievedPayment.getFees()[0].getVolume())
            )
        );
    }
}
