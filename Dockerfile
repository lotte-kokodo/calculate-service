FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/calculate-service-0.0.1-SNAPSHOT.jar CalculateService.jar
ENTRYPOINT ["java", "-jar", "CalculateService.jar"]