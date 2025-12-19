# ===== Build stage =====
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copy project files
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests


# ===== Runtime stage =====
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /usr/app/

# Copy only the jar from build stage
COPY --from=build /app/target/*.jar /usr/app/app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
