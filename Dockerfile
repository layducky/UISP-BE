# Use Maven official image (JDK + Maven pre-installed)
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy Maven configuration first (to cache dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src  ./src/

# Use a minimal runtime image
FROM maven:3.9.6-eclipse-temurin-17

# Copy JAR from build stage
COPY --from=builder /app /app

# Chạy Spring Boot
WORKDIR /app
CMD ["mvn", "spring-boot:run"]

