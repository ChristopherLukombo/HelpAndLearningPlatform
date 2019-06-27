package fr.esgi.service.dto;

import java.time.LocalDate;
import java.util.Objects;

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
		return Objects.hash(categoryId, content, creationDate, description, id, ownUserId, viewNumber, wording);
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
		return Objects.equals(categoryId, other.categoryId) && Objects.equals(content, other.content)
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(ownUserId, other.ownUserId)
				&& Objects.equals(viewNumber, other.viewNumber) && Objects.equals(wording, other.wording);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TrickDTO [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (wording != null) {
			builder.append("wording=");
			builder.append(wording);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (content != null) {
			builder.append("content=");
			builder.append(content);
			builder.append(", ");
		}
		if (categoryId != null) {
			builder.append("categoryId=");
			builder.append(categoryId);
			builder.append(", ");
		}
		if (ownUserId != null) {
			builder.append("ownUserId=");
			builder.append(ownUserId);
			builder.append(", ");
		}
		if (creationDate != null) {
			builder.append("creationDate=");
			builder.append(creationDate);
			builder.append(", ");
		}
		if (viewNumber != null) {
			builder.append("viewNumber=");
			builder.append(viewNumber);
		}
		builder.append("]");
		return builder.toString();
	}
}
