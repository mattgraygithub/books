FROM openjdk:11.0.11-slim

COPY /target /root

WORKDIR /root

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "books-1.0-SNAPSHOT.jar"]
