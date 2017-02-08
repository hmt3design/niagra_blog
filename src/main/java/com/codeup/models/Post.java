package com.codeup.models;

/**
 * Created by Harry on 2/8/17.
 */
public class Post {
    long id;
    String title;
    String body;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public Post() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
