# Use an official Gradle image to build the Spring Boot app
FROM gradle:8.10.2-jdk21 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the rest of the application code
COPY . .

RUN gradle clean shadowJar --no-daemon

# Use an official OpenJDK runtime as a base image for running the application
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Transfer the JAR file generated in the initial build stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Run the main class from the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]