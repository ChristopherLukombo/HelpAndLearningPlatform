package com.example.trips;

public class Category {
    private static int id;
    private String name;

    public Category(String name) {
        this.name = name;
        this.id ++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
