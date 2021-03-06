package com.example.trips.Models;

import java.io.Serializable;

public class Mark implements Serializable {

    private long id;
    private double note;
    private long trickId;
    private long userId;

    public Mark(double note, long trickId, long userId) {
        this.note = note;
        this.trickId = trickId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
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
}
