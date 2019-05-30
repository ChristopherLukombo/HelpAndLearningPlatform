package fr.esgi.service.dto;

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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(note);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((trickId == null) ? 0 : trickId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(note) != Double.doubleToLongBits(other.note))
			return false;
		if (trickId == null) {
			if (other.trickId != null)
				return false;
		} else if (!trickId.equals(other.trickId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "NotationDTO{" +
                "id=" + id +
                ", note=" + note +
                ", trickId=" + trickId +
                '}';
    }
}
