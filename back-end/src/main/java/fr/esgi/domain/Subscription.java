package fr.esgi.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id_subscription")
    private Category category;

    @Temporal(TemporalType.DATE)
    private Date subscriptionDate;

    @ManyToOne
    @JoinColumn(name = "user_id_subscription")
    private User user;

    public Subscription() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(category, that.category) &&
                Objects.equals(subscriptionDate, that.subscriptionDate) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, subscriptionDate, user);
    }
}
