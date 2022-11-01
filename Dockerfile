FROM openjdk:17-alpine
MAINTAINER ericD
COPY /build/libs/FareCalculator-0.0.1-SNAPSHOT.jar /home/FareCalculator-0.0.1-SNAPSHOT.jar
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-jar","/home/FareCalculator-0.0.1-SNAPSHOT.jar"]