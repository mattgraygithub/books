package com.books;

import com.books.domain.SearchResults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static com.books.TestData.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BooksMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void indexUrlReturnsOkAndSearchPageAndEmptyModel() throws Exception {
        returnsOkAndSearchPage(INDEX_URL, 0);
    }

    @Test
    public void searchUrlReturnsOkAndSearchResultsPageAndModelContainingSearchResults() throws Exception {
        when(restTemplate.getForObject(GOOGLE_BOOKS_URL, SearchResults.class)).thenReturn(SEARCH_RESULTS);

        mvc.perform(get(SEARCH_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(INDEX_VIEW))
                .andExpect(model().size(6))
                .andExpect(model().attribute(SEARCH_RESULTS_MODEL_NAME, SEARCH_RESULTS));
    }

    @Test
    public void randomUrlReturnsOkAndSearchPageAndEmptyModel() throws Exception {
        returnsOkAndSearchPage(RANDOM_URL, 1);
    }

    private void returnsOkAndSearchPage(String url, int modelSize) throws Exception {
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name(INDEX_VIEW))
                .andExpect(model().size(modelSize));
    }
}
