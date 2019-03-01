package com.books.dao;

import com.books.domain.SearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class GoogleBooksRepository {

    private static final int MIN_POSSIBLE_PAGE = 1;

    @Autowired
    private RestTemplate restTemplate;

    public SearchResults getSearchResults(String query, int page, int pageSize) {
        return restTemplate.getForObject(buildUrl(query, page, pageSize), SearchResults.class);
    }

    private static String buildUrl(String query, int page, int pageSize) {
        return UriComponentsBuilder
                .fromHttpUrl("https://www.googleapis.com/books/v1/volumes")
                .queryParam("q", query)
                .queryParam("startIndex", page == MIN_POSSIBLE_PAGE ? 0 : (page * pageSize) - pageSize)
                .queryParam("maxResults", pageSize)
                .build()
                .toUriString();
    }
}
