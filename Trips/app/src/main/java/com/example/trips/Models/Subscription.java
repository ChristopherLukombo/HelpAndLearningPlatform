package com.example.trips.Models;

import com.google.gson.annotations.SerializedName;

public class Subscription {

    @SerializedName("id")
    private long id;

    @SerializedName("subscriptionDate")
    private String subscriptionDate;

    @SerializedName("userId")
    private long userId;

    @SerializedName("categoryId")
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
