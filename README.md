# Books

This repository contains my solution for the following exercise:

---

Create an application that allows you to use the [Google Books API](https://developers.google.com/books/docs/overview) to search for books, and deploy it somewhere that we can access through a web browser.

This application should allow you to:
- Type in a query and display a list of books matching that query.
- Each item in the list should include the book's author, title, and publishing company, as well as a picture of the book.
- From each list item, you should also be able to navigate to more information about the book, but this information does not necessarily need to appear on a page within your application. In other words, this could link out to an external site with more information about that particular book.

---

## Prerequisites

- Java 8 (or later)
- Maven 3.5 (or later)

## Running the application

```
mvn spring-boot:run
```

Then go to [http://localhost:8080](http://localhost:8080)

Or if you prefer you can run the application in a docker container:

```
docker build -t books . 
docker run -p 8080:8080 books:latest
```

## Running the tests

```
mvn verify
```

## View the deployed application

The app is deployed on AWS here:
[http://ec2-18-212-105-75.compute-1.amazonaws.com/](http://ec2-18-212-105-75.compute-1.amazonaws.com/)

## Languages/frameworks used

- Java
- Maven
- Spring Boot
- Thymeleaf
- HtmlUnit 
