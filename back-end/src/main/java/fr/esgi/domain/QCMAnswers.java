package fr.esgi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "qcm_answers")
public class QCMAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private QCM qcm;

    private String answer;

    @JsonIgnore
    @ManyToOne
    private Trick trick;

    public QCMAnswers() {
        // Empty constructor for Hibernate.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QCM getQcm() {
        return qcm;
    }

    public void setQcm(QCM qcm) {
        this.qcm = qcm;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Trick getTrick() {
        return trick;
    }

    public void setTrick(Trick trick) {
        this.trick = trick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QCMAnswers that = (QCMAnswers) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(qcm, that.qcm) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(trick, that.trick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qcm, answer, trick);
    }
}
