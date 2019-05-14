package fr.esgi.service.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the Trick entity.
 */
public class TrickDTO {
	
    private Long id;

    @NotNull
    private String wording;

    @NotNull
    private String description;

    private Long categoryId;

    private Long ownUserId;

    private LocalDate creationDate;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrickDTO trickDTO = (TrickDTO) o;
        return Objects.equals(id, trickDTO.id) &&
                Objects.equals(wording, trickDTO.wording) &&
                Objects.equals(description, trickDTO.description) &&
                Objects.equals(categoryId, trickDTO.categoryId) &&
                Objects.equals(ownUserId, trickDTO.ownUserId) &&
                Objects.equals(creationDate, trickDTO.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wording, description, categoryId, ownUserId, creationDate);
    }

    @Override
    public String toString() {
        return "TrickDTO{" +
                "id=" + id +
                ", wording='" + wording + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", ownUserId=" + ownUserId +
                ", creationDate=" + creationDate +
                '}';
    }
}
