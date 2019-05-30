package com.example.trips.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trick implements Serializable {

    @SerializedName("categoryId")
    private long categoryId;

    @SerializedName("creationDate")
    private String creationDate;

    @SerializedName("content")
    private String content;

    @SerializedName("description")
    private String description;

    @SerializedName("wording")
    private String name;

    @SerializedName("id")
    private long id;

    @SerializedName("userId")
    private long userId;

    @SerializedName("viewNumber")
    private long viewNumber;

    public Trick(long categoryId, long userId, String creationDate, String description, String name, String content) {
        this.categoryId = categoryId;
        this.creationDate = creationDate;
        this.description = description;
        this.name = name;
        this.content = content;
        this.userId = userId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
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
}
