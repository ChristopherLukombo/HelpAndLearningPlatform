package com.example.trips.Models;

public class Comment {

    private long id;
    private String name;
    private long trickId;
    private long userId;
    private User user;


    public Comment(String name, long trickId, long userId) {
        this.name = name;
        this.trickId = trickId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTrickId() {
        return trickId;
    }

    public void setTrickId(long trickId) {
        this.trickId = trickId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
