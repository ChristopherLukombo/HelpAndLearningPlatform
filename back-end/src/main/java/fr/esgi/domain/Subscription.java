package fr.esgi.domain;

import java.io.Serializable;
import java.time.LocalDate;

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

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", trick=" + trick + ", subscriptionDate=" + subscriptionDate + ", user="
				+ user + "]";
	}
	
}
