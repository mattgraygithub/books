package com.books.domain;

import java.util.List;
import java.util.Objects;

public class SearchResults {

    private int totalItems;

    private List<Book> items;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<Book> getItems() {
        return items;
    }

    public void setItems(List<Book> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResults that = (SearchResults) o;
        return totalItems == that.totalItems && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalItems, items);
    }
}
