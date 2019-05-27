package com.example.trips.Models;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    private long id;

    @SerializedName("wording")
    private String name;

    public Category(String name) {
        this.name = name;
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
}
