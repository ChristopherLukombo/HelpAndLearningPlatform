package fr.esgi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * A trick.
 */
@Entity
public class Trick implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String wording;

    @NotNull
    private String description;
    
    @NotNull
    private String content;

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
    
    private Integer viewNumber;

    public Trick() {
    	// Empty constructor needed for Hibernate.
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((notations == null) ? 0 : notations.hashCode());
		result = prime * result + ((ownUser == null) ? 0 : ownUser.hashCode());
		result = prime * result + ((qcmAnswers == null) ? 0 : qcmAnswers.hashCode());
		result = prime * result + ((viewNumber == null) ? 0 : viewNumber.hashCode());
		result = prime * result + ((wording == null) ? 0 : wording.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trick other = (Trick) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (notations == null) {
			if (other.notations != null)
				return false;
		} else if (!notations.equals(other.notations))
			return false;
		if (ownUser == null) {
			if (other.ownUser != null)
				return false;
		} else if (!ownUser.equals(other.ownUser))
			return false;
		if (qcmAnswers == null) {
			if (other.qcmAnswers != null)
				return false;
		} else if (!qcmAnswers.equals(other.qcmAnswers))
			return false;
		if (viewNumber == null) {
			if (other.viewNumber != null)
				return false;
		} else if (!viewNumber.equals(other.viewNumber))
			return false;
		if (wording == null) {
			if (other.wording != null)
				return false;
		} else if (!wording.equals(other.wording))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trick [id=" + id + ", wording=" + wording + ", description=" + description + ", content=" + content
				+ ", category=" + category + ", notations=" + notations + ", comments=" + comments + ", qcmAnswers="
				+ qcmAnswers + ", ownUser=" + ownUser + ", creationDate=" + creationDate + ", viewNumber=" + viewNumber
				+ "]";
	}
}
