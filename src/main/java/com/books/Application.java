package com.books;

import com.books.dao.GoogleBooksRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GoogleBooksRepository googleBooksRepository() {
        return new GoogleBooksRepository();
    }

    @Bean
    public Pagination pagination() {
        return new Pagination();
    }
}
