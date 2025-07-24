# -------- Build app stage --------
FROM maven:3.9.9-eclipse-temurin-24-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean install 

# -------- Init app container --------
FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]