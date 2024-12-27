# Build stage
FROM maven:3.8.4-openjdk-17 as builder

# Set the working directory inside the container
WORKDIR /build

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Build the application with Maven
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /opt

# Copy the built JAR file from the builder stage
COPY --from=builder /build/target/*.jar /opt/app.jar

# Expose the new port (9090 in this case)
EXPOSE 8080

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]
