package com.books.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static com.books.TestData.buildSearchResults;
import static com.books.TestData.getFileContent;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RunWith(SpringRunner.class)
public class SearchResultsJsonTest {

    @Autowired
    private JacksonTester<SearchResults> json;

    @Test
    public void testSerialize() throws Exception {
        assertThat(json.write(buildSearchResults())).isEqualToJson("expected.json");
    }

    @Test
    public void testDeserialize() throws Exception {
        assertThat(json.parse(getFileContent("com/books/domain/expected.json"))).isEqualTo(buildSearchResults());
    }
}
