package com.example.testspring.Models;

public class Article {
    private int id;

    private String title;

    private String text;

    private String authour;

    public Article() {
    }

    public Article(int id, String title, String text, String authour) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.authour = authour;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getAuthour() {
        return authour;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthour(String authour) {
        this.authour = authour;
    }
}
