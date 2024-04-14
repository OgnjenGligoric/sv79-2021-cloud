# Start with a base image containing Java runtime
FROM eclipse-temurin:17-jre

# Install necessary OS tools
RUN apt-get update && apt-get install -y \
    bash \
    dos2unix

# Set the working directory in the Docker image
WORKDIR /app

# Environment variables for database configuration
ENV DB_HOST=localhost
ENV DB_PORT=5432
ENV DB_USER=postgres
ENV DB_PASSWORD=123
ENV DB_NAME=sv_79_2021_db
ENV DB_TABLE=sv_79_2021

# Copy the Maven wrapper executable and POM file
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Convert line endings and make the script executable
RUN dos2unix mvnw && chmod +x mvnw

# Optionally download dependencies to improve build layer caching
RUN ./mvnw dependency:go-offline

# Copy the project source code into the Docker image
COPY src src
# The final application jar will be named 'app.jar'
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the port that the application uses
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
