package com.example.trips.Models;

public class Subscription {

    private long id;
    private String subscriptionDate;
    private User creator;
    private Trick trick;

    public Subscription(String subscriptionDate, User creator, Trick trick) {
        this.subscriptionDate = subscriptionDate;
        this.creator = creator;
        this.trick = trick;
    }

    public long getId() {
        return id;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Trick getTrick() {
        return trick;
    }

    public void setTrick(Trick trick) {
        this.trick = trick;
    }
}
