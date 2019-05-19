package com.example.trips.Models;

public class Trick {

    private Category category;
    private String creationDate;
    private String description;
    private String name;
    private long id;

    public Trick(Category category, String creationDate, String description, String name) {
        this.category = category;
        this.creationDate = creationDate;
        this.description = description;
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
