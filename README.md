# LIDP Kubernetes Challenge

This base project includes a service that calculates the fare for a flight given the following inputs:

* Departure date of the flight
* Travel distance of the flight in miles
* Seat row (rows 0-3 are first class, etc.)

## Recommendations

LIDP recommends the Spring Framework for exposing the application and implementing the data access layer. However, its use is not required if you are more comfortable with an alternative approach.

Perhaps the most convenient way to create and deploy the application to a local Kubernetes cluster is to use [Docker Desktop](https://www.docker.com/products/docker-desktop/). The Kubernetes cluster can be enabled in the settings menu of the GUI. Alternatively, you can use a tool such as [minikube](https://minikube.sigs.k8s.io/docs/start/).

When deploying a spec, Kubernetes must pull the application image from a container registry. You are not required to push your image to a public container registry, such as Docker Hub. Instead, you can pull and run the docker registry image on your local system and push your application image to the registry container. In your spec, you then instruct Kubernetes to pull the image from the registry container. [Here](https://docs.docker.com/registry/deploying/#run-a-local-registry) are specific instructions on how to accomplish this.

## Resources

### Reference Documentation

#### Gradle
* [Official Gradle documentation](https://docs.gradle.org)

#### Spring
* [Spring Framework Overview](https://docs.spring.io/spring-framework/docs/current/reference/html/overview.html)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web)
* [JDBC API](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#data.sql)

#### Docker
* [Official Docker Documentation](https://docs.docker.com/)
* [Dockerfile Reference](https://docs.docker.com/engine/reference/builder/)

#### Kubernetes
* [Official Kubernetes Documentation](https://kubernetes.io/docs/home/)
* [Understanding Kubernetes Objects](https://kubernetes.io/docs/concepts/overview/working-with-objects/kubernetes-objects/)
* [`kubectl` Reference Guide](https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands)

### Guides
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)

## Questions?

If you encounter any difficulties with project setup, please email matthew.segreti@lidp.com for support!
