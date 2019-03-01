package com.books;

import org.junit.Test;

import static com.books.TestData.SEARCH_RESULTS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class PaginationTest {

    private static final int PAGE = 1;

    private static final int PAGE_SIZE = 1;

    private Pagination pagination = new Pagination();

    @Test
    public void returnsPagesList() {

        assertThat(pagination.getPaginationPages(PAGE, PAGE_SIZE, SEARCH_RESULTS), contains(1, 2, 3));
    }
}
