package com.books;

import com.books.domain.SearchResults;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class Pagination {

    private static final int MIN_POSSIBLE_PAGE = 1;

    private static final int SURROUNDING_PAGES_TO_DISPLAY = 4;

    public List<Integer> getPaginationPages(int page, int pageSize, SearchResults searchResults) {
        return rangeClosed(getMinPaginationPage(page), getMaxPaginationPage(searchResults, page, pageSize)).boxed().collect(toList());
    }

    private static int getMinPaginationPage(int page) {
        int desiredMinPage = page - SURROUNDING_PAGES_TO_DISPLAY;
        return desiredMinPage >= MIN_POSSIBLE_PAGE ? desiredMinPage : MIN_POSSIBLE_PAGE;
    }

    private static int getMaxPaginationPage(SearchResults searchResults, int page, int pageSize) {
        int desiredMaxPage = page + SURROUNDING_PAGES_TO_DISPLAY;
        int maxPossiblePage = searchResults.getTotalItems() / pageSize;
        return desiredMaxPage <= maxPossiblePage ? desiredMaxPage : maxPossiblePage;
    }
}
