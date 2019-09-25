package com.example.internetdemo;

import java.util.List;

public class Item {
    String title;
    String subtitle;
    String publishedDate;
    String pageCount;
    List<String> Autor;
    boolean saleability;
    String buyLink;

    public Item(String title, String subtitle, String publishedDate, String pageCount, List<String> autor, boolean saleability, String buyLink) {
        this.title = title;
        this.subtitle = subtitle;
        this.publishedDate = publishedDate;
        this.pageCount = pageCount;
        Autor = autor;
        this.saleability = saleability;
        this.buyLink = buyLink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public void setAutor(List<String> autor) {
        Autor = autor;
    }

    public void setSaleability(boolean saleability) {
        this.saleability = saleability;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getPageCount() {
        return pageCount;
    }

    public List<String> getAutor() {
        return Autor;
    }

    public boolean isSaleability() {
        return saleability;
    }

    public String getBuyLink() {
        return buyLink;
    }
}
