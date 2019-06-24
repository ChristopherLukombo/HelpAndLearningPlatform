package com.example.trips.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User implements Serializable {

    private boolean activated;
    private String countryOfResidence;
    private int authorityId;
    private String dateOfLastConnection;
    private String email;
    private String firstName;
    private String lastName;
    private List<User> friends;
    private String pseudo;
    private long id;
    private String imageUrl;
    private String langKey;

    private List<Subscription> subscriptions;

    public User(String email, String pseudo, String firstName, String lastName) {
        this.email = email;
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorityId = 1;
        this.countryOfResidence = "France";
        this.langKey = "FR";
        this.imageUrl = " ";
        this.activated = true;
        this.friends = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastName;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public long getId() {
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


    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public HashMap<String, String> getHashMap(String password){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("email", getEmail());
        hashMap.put("password", password);
        hashMap.put("lastName", getLastName());
        hashMap.put("firstName", getFirstName());
        hashMap.put("login", getPseudo());
        hashMap.put("langKey", getLangKey());
        hashMap.put("activated", String.valueOf(isActivated()));
        hashMap.put("countryOfResidence", getCountryOfResidence());
        hashMap.put("id", String.valueOf(getId()));
        hashMap.put("authorityId", String.valueOf(getAuthorityId()));
        hashMap.put("imageUrl", getImageUrl());

        return hashMap;
    }
}
