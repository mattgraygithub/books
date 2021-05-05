FROM maven:3.8.1-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11.0.11-slim
COPY --from=build /usr/src/app/target/books-1.0-SNAPSHOT.jar /usr/app/books-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/books-1.0-SNAPSHOT.jar"]
