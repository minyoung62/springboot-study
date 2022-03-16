package com.example.firstproject.dto;

public class ArticleForm {

    private String title;
    private String contents;

    public ArticleForm(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
