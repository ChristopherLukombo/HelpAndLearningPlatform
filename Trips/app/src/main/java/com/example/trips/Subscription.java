package com.example.trips;

public class Subscription {

    private static int id;
    private String subscriptionDate;
    private User creator;
    private Category category;

    public Subscription(String subscriptionDate, User creator, Category category) {
        this.subscriptionDate = subscriptionDate;
        this.creator = creator;
        this.category = category;
        this.id ++;
    }

    public int getId() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
