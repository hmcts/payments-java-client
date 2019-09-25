# Payments java client

[![Build Status](https://travis-ci.com/hmcts/payments-java-client.svg?branch=master)](https://travis-ci.com/hmcts/payments-java-client)
[ ![Download](https://api.bintray.com/packages/hmcts/hmcts-maven/payments-client/images/download.svg) ](https://bintray.com/hmcts/hmcts-maven/payments-client/_latestVersion)

This is a client library for interacting with the Payments application.

## Getting started

### Prerequisites

- [JDK 8](https://www.oracle.com/java)

## Usage

Just include the library as your dependency and you will be to use the client class.
You will also need to set the spring configuration property of `payments.api.url` 

A client (PaymentsClient) is provided for interacting with the PaymentsApi feign client to simplify the flow:
```java
@Service
class PaymentsService {
    private final PaymentsClient paymentsClient;
    
    PaymentsService(PaymentsClient paymentsClient) {
        this.paymentsClient = paymentsClient;
    }
    
    public Payment createPayment(String authorisation, PaymentRequest paymentRequest, String redirect) {
        return paymentsClient.createPayment(authorisation, paymentRequest, redirect);
    }

    public Payment retrievePayment(String authorisation, String paymentReference) {
        return paymentsClient.retrievePayment(authorisation, paymentReference);
    }
}

```

Components provided by this library will get automatically configured in a Spring context if `payments.api.url` configuration property is defined and does not equal `false`. 

## Building

The project uses [Gradle](https://gradle.org) as a build tool but you don't have install it locally since there is a
`./gradlew` wrapper script.  

To build project please execute the following command:

```bash
    ./gradlew build
```

## Developing

### Coding style tests

To run all checks (including unit tests) please execute the following command:

```bash
    ./gradlew check
```

## Versioning

We use [SemVer](http://semver.org/) for versioning.
For the versions available, see the tags on this repository.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
