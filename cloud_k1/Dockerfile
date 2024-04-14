# Start with a base image containing Java runtime
FROM eclipse-temurin:17-jre

# Set the working directory in the Docker image
WORKDIR /app


ENV DB_HOST=localhost
ENV DB_PORT=5432
ENV DB_USER=user
ENV DB_PASSWORD=password
ENV DB_NAME=mydatabase
ENV DB_TABLE=mytable

# Copy the Maven executable and POM file
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Optionally download dependencies to improve build layer caching
RUN ./mvnw dependency:go-offline

# Copy the project source code into the Docker image
COPY src src

# Package the application
RUN ./mvnw package -DskipTests

# The final application jar will be named 'app.jar'
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the port that the application uses
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]