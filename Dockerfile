FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /Reactive

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

#-------------------

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /Reactive/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]