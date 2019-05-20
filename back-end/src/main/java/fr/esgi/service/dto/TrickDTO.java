package fr.esgi.service.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Trick entity.
 */
public class TrickDTO {
	
    private Long id;

    @NotNull
    private String wording;

    @NotNull
    private String description;
    
    @NotNull
    private String content;

    private Long categoryId;

    private Long ownUserId;

    private LocalDate creationDate;
    
    private Integer viewNumber;

    public TrickDTO() {
        // Empty constructor needed for Jackson.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getOwnUserId() {
        return ownUserId;
    }

    public void setOwnUserId(Long ownUserId) {
        this.ownUserId = ownUserId;
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ownUserId == null) ? 0 : ownUserId.hashCode());
		result = prime * result + ((viewNumber == null) ? 0 : viewNumber.hashCode());
		result = prime * result + ((wording == null) ? 0 : wording.hashCode());
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
		TrickDTO other = (TrickDTO) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ownUserId == null) {
			if (other.ownUserId != null)
				return false;
		} else if (!ownUserId.equals(other.ownUserId))
			return false;
		if (viewNumber == null) {
			if (other.viewNumber != null)
				return false;
		} else if (!viewNumber.equals(other.viewNumber))
			return false;
		if (wording == null) {
			if (other.wording != null)
				return false;
		} else if (!wording.equals(other.wording))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrickDTO [id=" + id + ", wording=" + wording + ", description=" + description + ", content=" + content
				+ ", categoryId=" + categoryId + ", ownUserId=" + ownUserId + ", creationDate=" + creationDate
				+ ", viewNumber=" + viewNumber + "]";
	}
}
