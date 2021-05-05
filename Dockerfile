FROM openjdk:11.0.11-slim

COPY . /root

WORKDIR /root

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/books-1.0-SNAPSHOT.jar"]
