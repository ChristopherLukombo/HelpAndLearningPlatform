package com.example.trips;

public class Trick {

    private int categoryId;
    private String creationDate;
    private String description;
    private String name;
    private static int id;

    public Trick(int categoryId, String creationDate, String description, String name) {
        this.categoryId = categoryId;
        this.creationDate = creationDate;
        this.description = description;
        this.name = name;
        this.id ++;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryI) {
        this.categoryId = categoryI;
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

    public static int getId() {
        return id;
    }
}
