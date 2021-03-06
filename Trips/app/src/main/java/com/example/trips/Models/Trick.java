package com.example.trips.Models;

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
    private User user;
    private long viewNumber;
    private double markNotation;
    private boolean subscribed;
    private Subscription subscription;
    private Mark mark;

    public Trick(long categoryId, long userId, String creationDate, String description, String name, String content) {
        this.creationDate = creationDate;
        this.description = description;
        this.name = name;
        this.content = content;
        this.userId = userId;
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

    public double getMarkNotation() {
        return markNotation;
    }

    public void setMarkNotation(double markNotation) {
        this.markNotation = markNotation;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
