# Use official Eclipse Temurin Java 17 JRE Alpine base image
FROM eclipse-temurin:17-jre-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the generated executable JAR file into the container
COPY target/maven-github-demo-1.0-SNAPSHOT.jar app.jar

# Define default execution command
ENTRYPOINT ["java", "-jar", "app.jar"]
