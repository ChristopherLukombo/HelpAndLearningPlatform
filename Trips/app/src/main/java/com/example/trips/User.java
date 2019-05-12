package com.example.trips;

import java.util.ArrayList;
import java.util.List;

public class User {

    private boolean activated;
    private String countryOfResidence;
    private String dateOfLastConnection;
    private String email;
    private String firstname;
    private String lastname;
    private ArrayList<User> friends;
    private static int id;
    private String imageUrl;
    private String langKey;
    private String login;
    private ArrayList<Subscription> subscriptions;

    public User(String email, String firstname, String lastname, String login) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.id ++;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getDateOfLastConnection() {
        return dateOfLastConnection;
    }

    public void setDateOfLastConnection(String dateOfLastConnection) {
        this.dateOfLastConnection = dateOfLastConnection;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public static int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
