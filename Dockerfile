FROM openjdk:11.0.11-slim

COPY target/books-1.0-SNAPSHOT.jar books-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/books-1.0-SNAPSHOT.jar"]
