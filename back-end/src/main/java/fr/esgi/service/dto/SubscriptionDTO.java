package fr.esgi.service.dto;

import fr.esgi.domain.Subscription;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the Subscription entity.
 */
public class SubscriptionDTO {
	
    private Long id;

    private Long trickId;

    private LocalDate subscriptionDate;

    private Long userId;
    
    private Boolean finished;

    public Long getId() {
        return id;
    }

    public SubscriptionDTO() {
        // Empty constructor needed for Jackson.
    }

    public SubscriptionDTO(Subscription subscription) {
        this.id = subscription.getId();
        this.trickId = subscription.getTrick().getId();
        this.subscriptionDate = subscription.getSubscriptionDate();
        this.userId = subscription.getUser().getId();
        this.finished = subscription.getFinished();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrickId() {
		return trickId;
	}

	public void setTrickId(Long trickId) {
		this.trickId = trickId;
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

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	@Override
	public int hashCode() {
		return Objects.hash(finished, id, subscriptionDate, trickId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriptionDTO other = (SubscriptionDTO) obj;
		return Objects.equals(finished, other.finished) && Objects.equals(id, other.id)
				&& Objects.equals(subscriptionDate, other.subscriptionDate) && Objects.equals(trickId, other.trickId)
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubscriptionDTO [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (trickId != null) {
			builder.append("trickId=");
			builder.append(trickId);
			builder.append(", ");
		}
		if (subscriptionDate != null) {
			builder.append("subscriptionDate=");
			builder.append(subscriptionDate);
			builder.append(", ");
		}
		if (userId != null) {
			builder.append("userId=");
			builder.append(userId);
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
