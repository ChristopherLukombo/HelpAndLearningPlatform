package fr.esgi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.SubscriptionDTO;

/**
 * Service Implementation for managing Subscription.
 */
@Service
public interface SubscriptionService {
	
	

	/**
	 * Save a subscription.
	 * @param subscriptionDTO
	 * @return Subscription
	 */
    SubscriptionDTO save(SubscriptionDTO subscriptionDTO);
    
    /**
     * Returns true if user has already subscribed to the same trick.
     * @param SubscriptionDTO : the entity
     * @return boolean
     */
    boolean isSubscribed(SubscriptionDTO subscriptionDTO);
    
    /**
     * Returns a subscription by its id.
     * @param id : the id of the entity
     * @return the entity 
     */
    Optional<SubscriptionDTO> findOne(Long id);
    
    /**
     * Returns all subscriptions.
     * @return the list of entities
     */
    List<SubscriptionDTO> findAll();
    
    /**
     * Delete a subscription by its id.
     * @param id : the id of the subscription to delete.
     */
    void delete(Long id);
}
