FROM openjdk:17

EXPOSE 8080

WORKDIR /app

# Copy the JAR file to the container
COPY target/docker-redis-mongo.jar /app/docker

# Run the application
ENTRYPOINT [ "java","-jar","docker" ]