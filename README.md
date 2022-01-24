# favourite-service-consumer
***
## Description

This is a scratch project to explore the use of [Spring Cloud Contracts](https://spring.io/projects/spring-cloud-contract).

> Spring Cloud Contract Verifier moves TDD to the level of software architecture.

This is the **Consumer** side of the contract. Start by looking at the **Producer** side [here](https://github.com/jamiebrowning/favourite-service-producer)

This project represents the client of our trivial Favourite service. The Favourite service has published a set up of **stubs** that can be used by this consumer in their own integration tests.

In addition, the **version** information of the stubs can be used to decide on the suitability of deployment of this service.

## Install

Generate and run the tests. Test pass indicates that the consumer tests are valid against contracts defined in the producer stub.

```mvn clean install```

## Usage

 The [Spring Cloud Contract Verifier Plugin](https://cloud.spring.io/spring-cloud-contract/reference/html/maven-project.html) for Maven does the heavy lifting.
 
 It provides a `StubRunner` that can be configured via the `@AutoConfigureStubRunner` annotation.
