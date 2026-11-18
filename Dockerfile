# Etapa 1: Compilar el proyecto con Maven y Java 17
FROM amazoncorretto:17 AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
# O si usas el maven instalado en el sistema: RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación con una imagen ligera
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=builder /app/target/rest-stockverde-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]