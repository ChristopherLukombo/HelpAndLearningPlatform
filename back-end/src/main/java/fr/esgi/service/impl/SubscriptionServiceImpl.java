package fr.esgi.service.impl;

import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.domain.Subscription;
import fr.esgi.service.SubscriptionService;
import fr.esgi.service.dto.SubscriptionDTO;
import fr.esgi.service.mapper.SubscriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Service Implementation for managing Subscription.
 */
@Service("SubscriptionService")
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotationServiceImpl.class);

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
        LOGGER.debug("Request to save a Subscription");
        Subscription subscription = subscriptionMapper.subscriptionDTOToSubscription(subscriptionDTO);
        subscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.subscriptionToSubscriptionDTO(subscription);
    }
}
