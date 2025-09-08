#Stage 1: Develoment
FROM maven:3.9.11-amazoncorretto-17-alpine AS development
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean test

#Stage 2: Build

FROM maven:3.9.11-amazoncorretto-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

#Stage 3: Prodcution
FROM eclipse-temurin:17-jre-alpine AS production
RUN apk update
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser
WORKDIR /app
COPY --from=build /app/target/docker-redis-mongo.jar /app/docker-redis-mongo.jar
EXPOSE 8080
CMD ["java","-jar","docker-redis-mongo.jar"]