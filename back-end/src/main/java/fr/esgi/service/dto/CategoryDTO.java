package fr.esgi.service.dto;

import fr.esgi.domain.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * A DTO for the Category entity.
 */
public class CategoryDTO {
	
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String wording;

    public CategoryDTO() {
        // Empty constructor needed for Jackson.
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.wording = category.getWording();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(wording, that.wording);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wording);
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryDTO [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (wording != null) {
			builder.append("wording=");
			builder.append(wording);
		}
		builder.append("]");
		return builder.toString();
	}
}
