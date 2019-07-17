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
	public int hashCode() {
		return Objects.hash(id, question);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QCMDTO other = (QCMDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(question, other.question);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QCMDTO [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (question != null) {
			builder.append("question=");
			builder.append(question);
		}
		builder.append("]");
		return builder.toString();
	}
}
