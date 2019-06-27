package fr.esgi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "trick", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Notation> notations;

    @OneToMany(mappedBy = "trick", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "trick", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
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
		return Objects.hash(category, comments, content, creationDate, description, id, notations, ownUser, qcmAnswers,
				viewNumber, wording);
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
		return Objects.equals(category, other.category) && Objects.equals(comments, other.comments)
				&& Objects.equals(content, other.content) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(notations, other.notations) && Objects.equals(ownUser, other.ownUser)
				&& Objects.equals(qcmAnswers, other.qcmAnswers) && Objects.equals(viewNumber, other.viewNumber)
				&& Objects.equals(wording, other.wording);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trick [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (wording != null) {
			builder.append("wording=");
			builder.append(wording);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (content != null) {
			builder.append("content=");
			builder.append(content);
			builder.append(", ");
		}
		if (category != null) {
			builder.append("category=");
			builder.append(category);
			builder.append(", ");
		}
		if (notations != null) {
			builder.append("notations=");
			builder.append(notations);
			builder.append(", ");
		}
		if (comments != null) {
			builder.append("comments=");
			builder.append(comments);
			builder.append(", ");
		}
		if (qcmAnswers != null) {
			builder.append("qcmAnswers=");
			builder.append(qcmAnswers);
			builder.append(", ");
		}
		if (ownUser != null) {
			builder.append("ownUser=");
			builder.append(ownUser);
			builder.append(", ");
		}
		if (creationDate != null) {
			builder.append("creationDate=");
			builder.append(creationDate);
			builder.append(", ");
		}
		if (viewNumber != null) {
			builder.append("viewNumber=");
			builder.append(viewNumber);
		}
		builder.append("]");
		return builder.toString();
	}
}
