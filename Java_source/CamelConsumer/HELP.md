# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.15.RELEASE/maven-plugin/)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-amqp)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#production-ready)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Using Apache Camel with Spring Boot](https://camel.apache.org/spring-boot)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

For building the application,
1. Change dir to this directory
2. Execute 'mvn package'
3. docker login <registry_url:port>                   # If insecure registry, need to add an enrty in docker daemon.json (refer role kubeadm-master-role)
4. docker build -t <registry tag> -f dockerfile .     # registry tag should have nexus private registry tag like 'docker build -t 3.1.5.157:8082/clconsumer'
5. docker push <registry tag>

