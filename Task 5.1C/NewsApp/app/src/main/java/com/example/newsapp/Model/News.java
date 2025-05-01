package com.example.newsapp.Model;

import java.io.Serializable;

public class News implements Serializable {
    private String title;
    private int imageResId;
    private String description;

    public News(String title, int imageResId, String description) {
        this.title = title;
        this.imageResId = imageResId;
        this.description = description;
    }

    public String getTitle() { return title; }
    public int getImageResId() { return imageResId; }
    public String getDescription() { return description; }
}
