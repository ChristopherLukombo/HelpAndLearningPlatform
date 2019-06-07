package fr.esgi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.domain.Subscription;
import fr.esgi.service.SubscriptionService;
import fr.esgi.service.dto.SubscriptionDTO;
import fr.esgi.service.mapper.SubscriptionMapper;


/**
 * Service Implementation for managing Subscription.
 */
@Service("SubscriptionService")
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
	
    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

	/**
	 * Save a subscription.
	 * @param subscriptionDTO the entity to save
	 * @return Subscription the persisted entity
	 */
    public SubscriptionDTO saveSubscription(SubscriptionDTO subscriptionDTO) {
    	LOGGER.debug("Request to save Subscription : {}", subscriptionDTO);
    	Subscription subscription = subscriptionMapper.subscriptionDTOToSubscription(subscriptionDTO);
        subscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.subscriptionToSubscriptionDTO(subscription);
    }

    /**
     * Returns a subscription by its id.
     * @param id : the id of the entity
     * @return the entity 
     */
    @Transactional(readOnly = true)
	@Override
	public Optional<SubscriptionDTO> findOne(Long id) {
    	LOGGER.debug("Request to get Subscription : {}", id);
		final Optional<Subscription> subscription = subscriptionRepository.findById(id);
		return subscription.map(subscriptionMapper::subscriptionToSubscriptionDTO);
	}

	 /**
     * Returns all subscriptions.
     * @return the list of entities
     */
    @Transactional(readOnly = true)
	@Override
	public List<SubscriptionDTO> findAll() {
    	LOGGER.debug("Request to get all Subscriptions");
    	final List<Subscription> subscriptions = subscriptionRepository.findAll();
		return subscriptions.stream()
				.map(subscriptionMapper::subscriptionToSubscriptionDTO).collect(Collectors.toList());
	}

    /**
     * Delete a subscription by its id.
     * @param id : the id of the subscription to delete.
     */
	@Override
	public void delete(Long id) {
		LOGGER.debug("Request to delete Subscription : {}", id);
		subscriptionRepository.deleteById(id);
	}
}
