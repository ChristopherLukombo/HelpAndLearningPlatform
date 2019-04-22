package fr.esgi.service.dto;

import fr.esgi.domain.Subscription;

import java.time.LocalDate;

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


}
