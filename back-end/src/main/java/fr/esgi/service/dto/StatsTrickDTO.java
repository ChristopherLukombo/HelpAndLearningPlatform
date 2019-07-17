package fr.esgi.service.dto;

import java.util.Objects;

/**
 * A DTO StatsTrickDTO.
 */
public class StatsTrickDTO {

    private double mark;

    public StatsTrickDTO() {
        // Empty constructor needed for Jackson.
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

	@Override
	public int hashCode() {
		return Objects.hash(mark);
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
		return Double.doubleToLongBits(mark) == Double.doubleToLongBits(other.mark);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatsTrickDTO [mark=");
		builder.append(mark);
		builder.append("]");
		return builder.toString();
	}
}

