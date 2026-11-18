# Usamos una imagen oficial de Maven con Java 17 integrado
FROM maven:3.9.6-amazoncorretto-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=builder /app/target/rest-stockverde-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]