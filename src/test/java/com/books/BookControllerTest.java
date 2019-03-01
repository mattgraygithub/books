package com.books;

import com.books.dao.GoogleBooksRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static com.books.TestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    private static final int PAGE = 2;

    private static final int PAGE_SIZE = 1;

    @InjectMocks
    private BookController bookController;

    @Mock
    private GoogleBooksRepository googleBooksRepository;

    @Mock
    private Pagination pagination;

    private final Model model = new ExtendedModelMap();

    @Test
    public void indexWithoutQueryReturnsSearchPageAndEmptyModelAndDoesNotMakeAnyRestRequests() {
        String view = bookController.index(null, PAGE, PAGE_SIZE, model);

        assertThat(view, is(INDEX_VIEW));
        assertThat(model.asMap().keySet(), empty());
        verifyZeroInteractions(googleBooksRepository);
    }

    @Test
    public void indexWithQueryReturnsSearchResultsPageAndModelContainingSearchResultsAndMakesRestRequestToBooksApi() {
        when(googleBooksRepository.getSearchResults("history", PAGE, PAGE_SIZE)).thenReturn(SEARCH_RESULTS);
        when(pagination.getPaginationPages(PAGE, PAGE_SIZE, SEARCH_RESULTS)).
                thenReturn(new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                    add(3);
                }});

        String view = bookController.index("history", PAGE, PAGE_SIZE, model);

        assertThat(view, is(INDEX_VIEW));
        assertThat(model.asMap().keySet(), hasSize(6));
        assertThat(model.asMap(), hasEntry(SEARCH_RESULTS_MODEL_NAME, SEARCH_RESULTS));
        verify(googleBooksRepository).getSearchResults("history", PAGE, PAGE_SIZE);
        verify(pagination).getPaginationPages(PAGE, PAGE_SIZE, SEARCH_RESULTS);
    }

    @Test
    public void indexWithEmptyQueryReturnsSearchPageWithErrorMessageAndEmptyModelAndDoesNotMakeAnyRestRequests() {
        String view = bookController.index("", PAGE, PAGE_SIZE, model);

        assertThat(view, is(INDEX_VIEW));
        assertThat(model.asMap(), hasEntry(EMPTY_SEARCH, true));
        verifyZeroInteractions(googleBooksRepository);
    }

    @Test
    public void indexWithMultipleSpacesInQueryReturnsSearchPageWithErrorMessageAndEmptyModelAndDoesNotMakeAnyRestRequests() {
        String view = bookController.index("    ", PAGE, PAGE_SIZE, model);

        assertThat(view, is(INDEX_VIEW));
        assertThat(model.asMap(), hasEntry(EMPTY_SEARCH, true));
        verifyZeroInteractions(googleBooksRepository);
    }
}
