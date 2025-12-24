# ===== Build stage =====
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Install Maven
RUN apk add --no-cache maven

# Copy project files
COPY . .

# Build the application
RUN mvn clean package -DskipTests


# ===== Runtime stage =====
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /usr/app

# Copy only the jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
