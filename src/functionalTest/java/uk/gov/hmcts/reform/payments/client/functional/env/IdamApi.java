package uk.gov.hmcts.reform.payments.client.functional.env;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "idam-service-api",
        url = "${idam.api.url}",
        configuration = CoreFeignConfiguration.class
)
public interface IdamApi {
    @PostMapping(
            path = "/testing-support/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    void createUser(@RequestBody CreateUserRequest request);

    @GetMapping(
            path = "/details",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    UserDetails userInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorisation);

    @PostMapping(
            path = "/loginUser",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    LoginResponse loginUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @PostMapping(
            path = "/services",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    IdamService createService(@RequestHeader("Authorization") String authorization, @RequestBody IdamService service);

    @DeleteMapping(path = "/testing-support/services/{service}")
    void deleteService(@PathVariable("service") String service);
}
