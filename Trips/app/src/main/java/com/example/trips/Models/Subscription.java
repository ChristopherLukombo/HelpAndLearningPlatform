package com.example.trips.Models;

import java.io.Serializable;

public class Subscription implements Serializable {

    private long id;
    private String subscriptionDate;
    private long userId;
    private long trickId;
    private boolean isFinished;

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

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
