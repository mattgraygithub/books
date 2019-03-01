package com.books;

import com.books.domain.SearchResults;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.books.TestData.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerHtmlTest {

    @Autowired
    private WebClient webClient;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void indexUrlReturnsExpectedHtml() throws Exception {
        assertThat(getPageContent(INDEX_URL), is(getFileContent("index.html")));
    }

    @Test
    public void searchUrlReturnsExpectedHtml() throws Exception {
        when(restTemplate.getForObject(GOOGLE_BOOKS_URL, SearchResults.class)).thenReturn(buildSearchResults());

        assertThat(getPageContent(SEARCH_URL), is(getFileContent("searchResults.html")));
    }

    @Test
    public void randomUrlReturnsExpectedHtml() throws Exception {
        assertThat(getPageContent(RANDOM_URL), is(getFileContent("indexWithInvalidUrlMessage.html")));
    }

    @Test
    public void searchURLWithEmptySearchReturnsExpectedHtml() throws Exception {
        assertThat(getPageContent(EMPTY_SEARCH_URL), is(getFileContent("indexWithEmptySearchErrorMessage.html")));
    }

    private String getPageContent(String url) throws IOException {
        return ((HtmlPage) webClient.getPage(url)).asXml().replaceAll("\\s", "");
    }
}
