# Use Maven official image (JDK + Maven pre-installed)
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy Maven configuration first (to cache dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use a minimal runtime image
FROM eclipse-temurin:17-jdk

# Copy JAR from build stage
COPY --from=builder /app/target/*.jar app.jar

# Run the application
CMD ["java", "-jar", "/app.jar"]

