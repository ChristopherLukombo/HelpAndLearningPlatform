package fr.esgi.domain;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notation notation = (Notation) o;
        return Double.compare(notation.note, note) == 0 &&
                Objects.equals(id, notation.id) &&
                Objects.equals(trick, notation.trick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note, trick);
    }

    @Override
    public String toString() {
        return "Notation{" +
                "id=" + id +
                ", note=" + note +
                ", trick=" + trick +
                '}';
    }
}
