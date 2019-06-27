package fr.esgi.service.dto;

import java.util.Objects;

/**
 * A DTO StatsDTO.
 */
public class StatsDTO {

    private double mark;

    private long numberOfSubscribedUsers;

    public StatsDTO() {
        // Empty constructor needed for Jackson.
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public long getNumberOfSubscribedUsers() {
        return numberOfSubscribedUsers;
    }

    public void setNumberOfSubscribedUsers(long numberOfSubscribedUsers) {
        this.numberOfSubscribedUsers = numberOfSubscribedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatsDTO statsDTO = (StatsDTO) o;
        return Double.compare(statsDTO.mark, mark) == 0 &&
                Long.compare(statsDTO.numberOfSubscribedUsers, numberOfSubscribedUsers) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, numberOfSubscribedUsers);
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatsDTO [mark=");
		builder.append(mark);
		builder.append(", numberOfSubscribedUsers=");
		builder.append(numberOfSubscribedUsers);
		builder.append("]");
		return builder.toString();
	}
}
