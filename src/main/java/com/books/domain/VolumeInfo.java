package com.books.domain;

import java.util.List;
import java.util.Objects;

public class VolumeInfo {

    private List<String> authors;

    private String title;

    private String publisher;

    private ImageLinks imageLinks;

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumeInfo that = (VolumeInfo) o;
        return Objects.equals(authors, that.authors) &&
                Objects.equals(title, that.title) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(imageLinks, that.imageLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authors, title, publisher, imageLinks);
    }
}
