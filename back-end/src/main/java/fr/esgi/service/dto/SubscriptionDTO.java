package fr.esgi.service.dto;

import fr.esgi.domain.Subscription;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the Subscription entity.
 */
public class SubscriptionDTO {
	
    private Long id;

    private Long categoryId;

    private LocalDate subscriptionDate;

    private Long userId;

    public Long getId() {
        return id;
    }

    public SubscriptionDTO() {
        // Empty constructor needed for Jackson.
    }

    public SubscriptionDTO(Subscription subscription) {
        this.id = subscription.getId();
        this.categoryId = subscription.getCategory().getId();
        this.subscriptionDate = subscription.getSubscriptionDate();
        this.userId = subscription.getUser().getId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionDTO that = (SubscriptionDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(subscriptionDate, that.subscriptionDate) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, subscriptionDate, userId);
    }

    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", subscriptionDate=" + subscriptionDate +
                ", userId=" + userId +
                '}';
    }
}
