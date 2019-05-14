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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDTO that = (ContactDTO) o;
        return Objects.equals(subject, that.subject) &&
                Objects.equals(information, that.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, information);
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "subject='" + subject + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
}
