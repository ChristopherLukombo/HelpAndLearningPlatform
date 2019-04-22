package fr.esgi.service.dto;

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
}
