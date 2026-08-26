package com.example.graphapp.model;

public class Movie {

    private String id;
    private String title;
    private int releaseYear;
    private String description;

    public Movie() {
    }

    public Movie(String id, String title, int releaseYear, String description) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}