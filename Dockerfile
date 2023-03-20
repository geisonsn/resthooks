# Build stage
FROM maven:3.9.0 AS build
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
RUN mvn -f ./pom.xml -DskipTests clean package

# Package stage
FROM openjdk:19-jdk-alpine
COPY --from=build /home/app/target/resthooks-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
