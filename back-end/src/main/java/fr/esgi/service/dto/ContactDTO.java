package fr.esgi.service.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * A DTO for the Contact.
 */
public class ContactDTO {

    @NotNull
    private String subject;

    @NotNull
    private String information;

    public ContactDTO() {
        // Default constructor needed for Jackson.
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

	@Override
	public int hashCode() {
		return Objects.hash(information, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactDTO other = (ContactDTO) obj;
		return Objects.equals(information, other.information) && Objects.equals(subject, other.subject);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContactDTO [");
		if (subject != null) {
			builder.append("subject=");
			builder.append(subject);
			builder.append(", ");
		}
		if (information != null) {
			builder.append("information=");
			builder.append(information);
		}
		builder.append("]");
		return builder.toString();
	}
}
