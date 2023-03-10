# Use an official Gradle runtime as a parent image
FROM gradle:7.5.1-jdk11 as build

# Set the working directory to /app
WORKDIR /app

# Copy the build files to the container
COPY . .

# Build the application
RUN ./gradlew build

# Use an official OpenJDK runtime as a parent image
FROM openjdk:11.0.11-jre-slim-buster

# Copy the built application from the previous stage
COPY --from=build /app/build/libs/WeHome-idea-0.0.1-SNAPSHOT.jar .

EXPOSE 8080
# Define the command to run the application
CMD ["java", "-jar", "WeHome-idea-0.0.1-SNAPSHOT.jar"]
