package fr.esgi.service.dto;

public class StatsDTO {

    private double mark;

    private double numberOfSubscribedUsers;

    public StatsDTO() {
        // Empty constructor needed for Jackson.
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getNumberOfSubscribedUsers() {
        return numberOfSubscribedUsers;
    }

    public void setNumberOfSubscribedUsers(double numberOfSubscribedUsers) {
        this.numberOfSubscribedUsers = numberOfSubscribedUsers;
    }
}
