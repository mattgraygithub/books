package com.books.dao;

import com.books.domain.SearchResults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static com.books.TestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoogleBooksRepositoryTest {

    private static final int PAGE = 2;

    private static final int PAGE_SIZE = 1;

    @InjectMocks
    private GoogleBooksRepository googleBooksRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void returnsSearchResults() {
        when(restTemplate.getForObject(GOOGLE_BOOKS_URL, SearchResults.class)).thenReturn(SEARCH_RESULTS);

        assertThat(googleBooksRepository.getSearchResults("history", PAGE, PAGE_SIZE), is(SEARCH_RESULTS));
        verify(restTemplate).getForObject(GOOGLE_BOOKS_URL, SearchResults.class);
    }
}