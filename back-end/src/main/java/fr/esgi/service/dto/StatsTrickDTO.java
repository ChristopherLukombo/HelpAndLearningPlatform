package fr.esgi.service.dto;

import java.util.Objects;

/**
 * A DTO StatsTrickDTO.
 */
public class StatsTrickDTO {

    private double mark;
    
    private int numberOfSubscribedUsers;
    
    private int numberOfComments;

    public StatsTrickDTO() {
        // Empty constructor needed for Jackson.
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

	public int getNumberOfSubscribedUsers() {
		return numberOfSubscribedUsers;
	}

	public void setNumberOfSubscribedUsers(int numberOfSubscribedUsers) {
		this.numberOfSubscribedUsers = numberOfSubscribedUsers;
	}

	public int getNumberOfComments() {
		return numberOfComments;
	}

	public void setNumberOfComments(int numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mark, numberOfComments, numberOfSubscribedUsers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatsTrickDTO other = (StatsTrickDTO) obj;
		return Double.doubleToLongBits(mark) == Double.doubleToLongBits(other.mark)
				&& numberOfComments == other.numberOfComments
				&& numberOfSubscribedUsers == other.numberOfSubscribedUsers;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatsTrickDTO [mark=");
		builder.append(mark);
		builder.append(", numberOfSubscribedUsers=");
		builder.append(numberOfSubscribedUsers);
		builder.append(", numberOfComments=");
		builder.append(numberOfComments);
		builder.append("]");
		return builder.toString();
	}

	
}

