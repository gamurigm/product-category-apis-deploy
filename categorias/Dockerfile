
FROM maven:3.9.6-eclipse-temurin-17-alpine as builder

WORKDIR /app

COPY ./pom.xml ./
COPY ./src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/target/categorias-0.0.1-SNAPSHOT.jar .
EXPOSE 8089

ENTRYPOINT ["java", "-jar", "categorias-0.0.1-SNAPSHOT.jar"]
