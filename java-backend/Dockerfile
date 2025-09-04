# Use OpenJDK 21
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/factcheck-0.0.1-SNAPSHOT.jar"]

