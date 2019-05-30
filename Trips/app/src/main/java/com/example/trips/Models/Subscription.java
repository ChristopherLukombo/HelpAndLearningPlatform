package com.example.trips.Models;

public class Subscription {

    private long id;
    private String subscriptionDate;
    private long userId;
    private long trickId;

    public Subscription(String subscriptionDate, long userId, long trickId) {
        this.subscriptionDate = subscriptionDate;
        this.userId = userId;
        this.trickId = trickId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTrickId() {
        return trickId;
    }

    public void setTrickId(long trickId) {
        this.trickId = trickId;
    }
}
