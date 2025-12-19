# ===== Build stage =====
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

RUN apk add --no-cache maven

# Copy repo
COPY . .

# Move into Maven project directory
WORKDIR /app/backend

# Build application
RUN mvn clean package -DskipTests


# ===== Runtime stage =====
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /usr/app

COPY --from=build /app/backend/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
