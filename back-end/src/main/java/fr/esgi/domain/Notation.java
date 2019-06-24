package fr.esgi.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * A notation.
 */
@Entity
public class Notation implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double note;

    @ManyToOne
    @JoinColumn(name = "trick_id_notation")
    private Trick trick;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notation() {
        // Empty constructor needed for Hibernate.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Trick getTrick() {
        return trick;
    }

    public void setTrick(Trick trick) {
        this.trick = trick;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(note);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((trick == null) ? 0 : trick.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Notation other = (Notation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(note) != Double.doubleToLongBits(other.note))
			return false;
		if (trick == null) {
			if (other.trick != null)
				return false;
		} else if (!trick.equals(other.trick))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notation [id=" + id + ", note=" + note + ", trick=" + trick + ", user=" + user + "]";
	}
}
