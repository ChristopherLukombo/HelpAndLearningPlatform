package fr.esgi.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Notation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double note;

    @ManyToOne
    @JoinColumn(name = "trick_id_notation")
    private Trick trick;

    public Notation() { }

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
}
