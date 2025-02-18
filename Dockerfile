# Use a base image with JDK
FROM openjdk:17-jdk-slim

WORKDIR /app
# Copy the compiled Spring Boot JAR into the container
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

