package fr.esgi.service.dto;

import java.util.Objects;

/**
 * A DTO for the QCMAnswers entity.
 */
public class QCMAnswersDTO {
	
    private Long id;

    private Long qcmId;

    private String answer;

    private Long trickId;

    public QCMAnswersDTO() {
        // Empty constructor needed for Jackson.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQcmId() {
        return qcmId;
    }

    public void setQcmId(Long qcmId) {
        this.qcmId = qcmId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getTrickId() {
        return trickId;
    }

    public void setTrickId(Long trickId) {
        this.trickId = trickId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QCMAnswersDTO that = (QCMAnswersDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(qcmId, that.qcmId) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(trickId, that.trickId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qcmId, answer, trickId);
    }

    @Override
    public String toString() {
        return "QCMAnswersDTO{" +
                "id=" + id +
                ", qcmId=" + qcmId +
                ", answer='" + answer + '\'' +
                ", trickId=" + trickId +
                '}';
    }
}
