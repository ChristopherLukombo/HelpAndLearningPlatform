package fr.esgi.service;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.SubscriptionDTO;

@Service
public interface SubscriptionService {

	/**
	 * Save a subscription.
	 * @param subscriptionDTO
	 * @return Subscription
	 */
    SubscriptionDTO saveSubscription(SubscriptionDTO subscriptionDTO);
}
