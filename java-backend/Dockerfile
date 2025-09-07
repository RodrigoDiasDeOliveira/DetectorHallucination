# Etapa 1: Compilar o projeto com Maven
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
# Cache de dependências
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Criar imagem de execução
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/factcheck-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# Configurações JVM otimizadas
ENV JAVA_OPTS="-Xms512m -Xmx1g"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]