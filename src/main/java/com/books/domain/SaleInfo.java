package com.books.domain;

import java.util.Objects;

public class SaleInfo {

    private String buyLink;

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleInfo saleInfo = (SaleInfo) o;
        return Objects.equals(buyLink, saleInfo.buyLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyLink);
    }
}
