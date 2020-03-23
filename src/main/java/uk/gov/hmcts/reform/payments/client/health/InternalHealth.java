package uk.gov.hmcts.reform.payments.client.health;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.boot.actuate.health.Status;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InternalHealth {

    private final Status status;

    @JsonCreator
    public InternalHealth(@JsonProperty("status") Status status) {
        this.status = status;
    }
}
