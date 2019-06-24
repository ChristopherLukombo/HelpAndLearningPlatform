package fr.esgi.service.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * A DTO for the QCM entity.
 */
public class QCMDTO {
	
    private Long id;

    @NotNull
    private String question;

    public QCMDTO() {
    	// Empty constructor needed for Jackson.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QCMDTO qcm = (QCMDTO) o;
        return Objects.equals(id, qcm.id) &&
                Objects.equals(question, qcm.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question);
    }

    @Override
    public String toString() {
        return "QCMDTO{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }
}
