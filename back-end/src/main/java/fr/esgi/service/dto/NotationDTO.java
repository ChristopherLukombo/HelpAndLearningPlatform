package fr.esgi.service.dto;

import java.util.Objects;

/**
 * A DTO for the Notation entity.
 */
public class NotationDTO {
	
    private Long id;

    private double note;

    private Long trickId;
    
    private Long userId;

    public NotationDTO() {
        // Empty constructor needed for Jackson.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Long getTrickId() {
        return trickId;
    }

    public void setTrickId(Long trickId) {
        this.trickId = trickId;
    }
    
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, note, trickId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotationDTO other = (NotationDTO) obj;
		return Objects.equals(id, other.id) && Double.doubleToLongBits(note) == Double.doubleToLongBits(other.note)
				&& Objects.equals(trickId, other.trickId) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotationDTO [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		builder.append("note=");
		builder.append(note);
		builder.append(", ");
		if (trickId != null) {
			builder.append("trickId=");
			builder.append(trickId);
			builder.append(", ");
		}
		if (userId != null) {
			builder.append("userId=");
			builder.append(userId);
		}
		builder.append("]");
		return builder.toString();
	}
}
