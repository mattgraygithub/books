package com.books.domain;

import java.util.Objects;

public class Book {

    private VolumeInfo volumeInfo;

    private SaleInfo saleInfo;

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(volumeInfo, book.volumeInfo) &&
                Objects.equals(saleInfo, book.saleInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volumeInfo, saleInfo);
    }
}
