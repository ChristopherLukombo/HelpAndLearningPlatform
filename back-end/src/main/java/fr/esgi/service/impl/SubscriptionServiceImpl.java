package fr.esgi.service.impl;

import java.time.LocalDate;
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
	public SubscriptionDTO save(SubscriptionDTO subscriptionDTO) {
		LOGGER.debug("Request to save Subscription : {}", subscriptionDTO);
		Subscription subscription = subscriptionMapper.subscriptionDTOToSubscription(subscriptionDTO);
		if (null == subscription.getSubscriptionDate()) {
			subscription.setSubscriptionDate(LocalDate.now());
		}
		subscription = subscriptionRepository.save(subscription);
		return subscriptionMapper.subscriptionToSubscriptionDTO(subscription);
	}

	/**
	 * Returns true if user has already subscribed to the same trick.
	 * @param SubscriptionDTO : the entity
	 * @return boolean
	 */
	@Transactional(readOnly = true)
	@Override
	public boolean isSubscribed(SubscriptionDTO subscriptionDTO) {
		return !subscriptionRepository.findAllByTrickId(subscriptionDTO.getTrickId(), subscriptionDTO.getUserId()).isEmpty();
	}

	/**
	 * Set a subscription finished to true.
	 * @param subscriptionId
	 * @return Subscription the entity updated
	 */
	@Override
	public SubscriptionDTO setToFinished(Long subscriptionId) {
		Subscription subscription = subscriptionRepository.findBySubscriptionIdAndUserId(subscriptionId);
		subscription.setFinished(true);
		subscription = subscriptionRepository.saveAndFlush(subscription);
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
		return subscriptionRepository.findById(id)
				.map(subscriptionMapper::subscriptionToSubscriptionDTO);
	}

	/**
	 * Returns all subscriptions.
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	@Override
	public List<SubscriptionDTO> findAll() {
		LOGGER.debug("Request to get all Subscriptions");
		return subscriptionRepository.findAll().stream()
				.map(subscriptionMapper::subscriptionToSubscriptionDTO)
				.collect(Collectors.toList());
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
