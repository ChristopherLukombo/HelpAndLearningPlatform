package fr.esgi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * A subscription.
 */
@Entity
public class Subscription implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Trick trick;

    private LocalDate subscriptionDate;

    @ManyToOne
    private User user;
    
    private Boolean finished;

    public Subscription() {
    	// Empty constructor needed for Hibernate.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trick getTrick() {
		return trick;
	}

	public void setTrick(Trick trick) {
		this.trick = trick;
	}

	public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	@Override
	public int hashCode() {
		return Objects.hash(finished, id, subscriptionDate, trick, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		return Objects.equals(finished, other.finished) && Objects.equals(id, other.id)
				&& Objects.equals(subscriptionDate, other.subscriptionDate) && Objects.equals(trick, other.trick)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subscription [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (trick != null) {
			builder.append("trick=");
			builder.append(trick);
			builder.append(", ");
		}
		if (subscriptionDate != null) {
			builder.append("subscriptionDate=");
			builder.append(subscriptionDate);
			builder.append(", ");
		}
		if (user != null) {
			builder.append("user=");
			builder.append(user);
			builder.append(", ");
		}
		if (finished != null) {
			builder.append("finished=");
			builder.append(finished);
		}
		builder.append("]");
		return builder.toString();
	}
}
