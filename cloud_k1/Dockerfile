# Start with a base image containing Java runtime
FROM eclipse-temurin:17-jre

# Install necessary OS tools
RUN apt-get update && apt-get install -y \
    bash \
    dos2unix

# Set the working directory in the Docker image
WORKDIR /app
ARG DB_HOST
ARG DB_PORT
ARG DB_NAME
ARG DB_USERNAME
ARG DB_PASSWORD

ENV DB_HOST=$DB_HOST
ENV DB_PORT=$DB_PORT
ENV DB_NAME=$DB_NAME
ENV DB_USERNAME=$DB_USERNAME
ENV DB_PASSWORD=$DB_PASSWORD

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
