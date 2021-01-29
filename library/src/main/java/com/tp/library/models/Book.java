package com.tp.library.models;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private Integer id;
    private String title;
    private List<String> authors;

    public Book(Integer id, String title, List<String> authors, Integer publicationYear) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publicationYear = publicationYear;
    }

    public Book(Book that) {
        this.id = that.id;
        this.title = that.title;
        this.authors = new ArrayList<>();
        for (String toCopy : that.authors) {
            this.authors.add(toCopy);
        }
        this.publicationYear = that.publicationYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    private Integer publicationYear;
}
