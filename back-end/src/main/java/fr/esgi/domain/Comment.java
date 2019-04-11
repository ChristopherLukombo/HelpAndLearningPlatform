package fr.esgi.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "trick_id_comment")
    private Trick trick;

    public Comment() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(name, comment.name) &&
                Objects.equals(trick, comment.trick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, trick);
    }
}
