package com.books;

import com.books.domain.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public final class TestData {

    public static final SearchResults SEARCH_RESULTS = buildSearchResults();

    public static final String INDEX_VIEW = "index";

    public static final String SEARCH_RESULTS_MODEL_NAME = "searchResults";

    public static final String SEARCH_URL = "/?q=history&page=2&pageSize=1";

    public static final String INDEX_URL = "/";

    public static final String RANDOM_URL = "/random";

    public static final String GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=history&startIndex=1&maxResults=1";

    public static final String EMPTY_SEARCH = "emptySearch";

    public static final String EMPTY_SEARCH_URL = "/?q=";

    private TestData() {
    }

    public static SearchResults buildSearchResults() {
        SearchResults searchResults = new SearchResults();
        searchResults.setItems(asList(buildBook("book1", 1), buildBook("book2", 2), buildBook("book3", 3)));
        searchResults.setTotalItems(searchResults.getItems().size());
        return searchResults;
    }

    public static String getFileContent(String file) throws IOException {
        return StreamUtils.copyToString(new ClassPathResource(file).getInputStream(), Charset.defaultCharset()).replaceAll("\\s", "");
    }

    private static Book buildBook(String bookName, int numberOfAuthors) {
        Book book = new Book();
        SaleInfo saleInfo = new SaleInfo();
        saleInfo.setBuyLink(bookName + "BuyLink");
        book.setSaleInfo(saleInfo);
        VolumeInfo volumeInfo = new VolumeInfo();
        volumeInfo.setAuthors(IntStream.range(0, numberOfAuthors).mapToObj(i -> bookName + "Author" + i).collect(Collectors.toList()));
        ImageLinks imageLinks = new ImageLinks();
        imageLinks.setSmallThumbnail(bookName + "SmallThumbnail");
        imageLinks.setThumbnail(bookName + "Thumbnail");
        volumeInfo.setImageLinks(imageLinks);
        volumeInfo.setPublisher(bookName + "Publisher");
        volumeInfo.setTitle(bookName + "Title");
        book.setVolumeInfo(volumeInfo);
        return book;
    }
}
