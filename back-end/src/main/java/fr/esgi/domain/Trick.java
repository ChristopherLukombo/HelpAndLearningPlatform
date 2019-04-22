package fr.esgi.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Trick {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wording;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "trick", fetch = FetchType.LAZY)
    private List<Notation> notations;

    @OneToMany(mappedBy = "trick", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "trick", fetch = FetchType.LAZY)
    private List<QCMAnswers> qcmAnswers;

    @ManyToOne
    @JoinColumn(name = "own_user_id_trick")
    private User ownUser;

    private LocalDate creationDate;


    public Trick() { }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Notation> getNotations() {
        return notations;
    }

    public void setNotations(List<Notation> notations) {
        this.notations = notations;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<QCMAnswers> getQcmAnswers() {
        return qcmAnswers;
    }

    public void setQcmAnswers(List<QCMAnswers> qcmAnswers) {
        this.qcmAnswers = qcmAnswers;
    }

    public User getOwnUser() {
        return ownUser;
    }

    public void setOwnUser(User ownUser) {
        this.ownUser = ownUser;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trick trick = (Trick) o;
        return Objects.equals(id, trick.id) &&
                Objects.equals(wording, trick.wording) &&
                Objects.equals(description, trick.description) &&
                Objects.equals(category, trick.category) &&
                Objects.equals(notations, trick.notations) &&
                Objects.equals(comments, trick.comments) &&
                Objects.equals(qcmAnswers, trick.qcmAnswers) &&
                Objects.equals(ownUser, trick.ownUser) &&
                Objects.equals(creationDate, trick.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wording, description, category, notations, comments, qcmAnswers, ownUser, creationDate);
    }
}
