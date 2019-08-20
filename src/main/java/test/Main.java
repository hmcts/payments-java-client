package test;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import uk.gov.hmcts.reform.payments.client.PaymentRequest;
import uk.gov.hmcts.reform.payments.client.PaymentsApi;
import uk.gov.hmcts.reform.payments.client.PaymentsClient;
import uk.gov.hmcts.reform.payments.client.models.Fee;
import uk.gov.hmcts.reform.payments.client.models.Payment;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class Main {
    public static void main(String... args) {
        if (args.length == 0) {
            System.err.println("Usage: java -jar payments-java-client.jar {sessionId}");
            System.err.println("\tsessionId:\tLog in to an IdAM service and extract the SESSION_ID cookie");
        }
        PaymentsApi api = createFeignClient();
        PaymentsClient client = new PaymentsClient(api, Main::generateServiceToken);

        System.out.println("Create Payment");
        System.out.println("--------------");
        Payment createdPayment = client.createPayment(
                args[0],
                PaymentRequest.builder()
                        .caseReference(UUID.randomUUID().toString())
                        .ccdCaseNumber("UNKNOWN")
                        .description("Money Claims fees")
                        .service("CMC")
                        .currency("GBP")
                        .siteId("AA00")
                        .fees(new Fee[0])
                        .amount(BigDecimal.TEN)
                        .build(),
                "http://localhost"
        );
        System.out.println(createdPayment);

        System.out.println();

        System.out.println("Retrieve Payment");
        System.out.println("----------------");
        Payment retrievedPayment = client.retrievePayment(args[0], createdPayment.getReference());
        System.out.println(retrievedPayment);
    }

    private static PaymentsApi createFeignClient() {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(PaymentsApi.class, "http://localhost:4421");
    }

    public static String generateServiceToken() {
        try {
            URL url = new URL("http://localhost:4552/testing-support/lease");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            osw.write("{\"microservice\":\"cmc\",\"oneTimePassword\":\"12345678\"}");
            osw.flush();
            osw.close();
            os.close();
            con.connect();

            String result;
            BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result2 = bis.read();
            while (result2 != -1) {
                buf.write((byte) result2);
                result2 = bis.read();
            }
            result = buf.toString();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Main() {
        // no op
    }
}
