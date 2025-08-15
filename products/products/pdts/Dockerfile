FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/target/pdts-0.0.1-SNAPSHOT.jar .
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "pdts-0.0.1-SNAPSHOT.jar"]