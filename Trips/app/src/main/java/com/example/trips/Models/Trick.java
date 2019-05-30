package com.example.trips.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trick implements Serializable {

    private long categoryId;
    private Category category;
    private String creationDate;
    private String content;
    private String description;
    private String name;
    private long id;
    private long userId;
    private long viewNumber;
    public int mark;

    public Trick(long categoryId, long userId, String creationDate, String description, String name, String content) {
        this.creationDate = creationDate;
        this.description = description;
        this.name = name;
        this.content = content;
        this.userId = userId;
        this.mark = 0;
        this.categoryId = categoryId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(long viewNumber) {
        this.viewNumber = viewNumber;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
