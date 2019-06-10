package fr.esgi.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.dao.TrickRepository;
import fr.esgi.domain.Category;
import fr.esgi.domain.Subscription;
import fr.esgi.domain.Trick;
import fr.esgi.service.TrickService;
import fr.esgi.service.dto.TrickDTO;
import fr.esgi.service.mapper.TrickMapper;

/**
 * Service Implementation for managing Trick.
 */
@Service("TrickService")
@Transactional
public class TrickServiceImpl implements TrickService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotationServiceImpl.class);

    private final TrickRepository trickRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final TrickMapper trickMapper;

    @Autowired
    public TrickServiceImpl(TrickRepository trickRepository, SubscriptionRepository subscriptionRepository,
                            TrickMapper trickMapper) {
        this.trickRepository = trickRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.trickMapper = trickMapper;
    }
    
    /**
	 * Find all tricks.
	 * @return list of entities
	 */
    @Transactional(readOnly = true)
    @Override
	public List<TrickDTO> findAll() {
		LOGGER.debug("Request to retrieve all tricks");
		return trickRepository.findAll().stream()
				.map(trickMapper::trickToTrickDTO).collect(Collectors.toList());
	}
    
    /**
     * Find all tricks by user id.
	 * @param userId 
	 * @return list of entities
     */
    @Transactional(readOnly = true)
    @Override
    public List<TrickDTO> findAllByUserId(Long userId) {
    	LOGGER.debug("Request to get all tricks by userId: {}", userId);
    	final List<Subscription> subscriptions = subscriptionRepository.findAllByUserId(userId);
    	if (null == subscriptions || subscriptions.isEmpty()) {
    		return Collections.emptyList();
    	}
    	return subscriptions.stream()
    			.map(Subscription::getTrick)
    			.map(trickMapper::trickToTrickDTO)
    			.map(Optional::ofNullable)
    			.filter(Optional::isPresent)
    			.map(Optional::get)
    			.distinct()
    			.collect(Collectors.toList());
    }

	/**
     * Find all new tricks which are available according to the id
	 * @param userId the id user
	 * @return list of entities
     */
    @Transactional(readOnly = true)
    @Override
    public List<TrickDTO> findAllNewTricksAvailableByUserId(Long userId) {
        LOGGER.debug("Request to get all Tricks available by userId: {}", userId);
        final List<Subscription> subscriptions = subscriptionRepository.findAllByUserId(userId);
        if (null == subscriptions || subscriptions.isEmpty()) {
            return Collections.emptyList();
        }
        final List<Trick> allNewTricksAvailable = new ArrayList<>();
        final LocalDate dateBefore = getDateBefore();
        
        for (Subscription subscription : subscriptions) {
        	final Category category = subscription.getTrick().getCategory();
            allNewTricksAvailable.addAll(
                    trickRepository.findAllNewTricksAvailableByDateAndCategoryId(dateBefore, category.getId()));
        }
        return allNewTricksAvailable.stream()
                .map(trickMapper::trickToTrickDTO)
                .collect(Collectors.toList());
    }

    private LocalDate getDateBefore() {
        final LocalDate today = LocalDate.now();
        return today.minusDays(2);
    }

    /**
     * Update a Trick
     * @param trick
     * @return entity
     */
	@Override
	public TrickDTO update(TrickDTO trickDTO) {
	    LOGGER.debug("Request to update a trick: {}", trickDTO);
		Trick trick = trickMapper.trickDTOToTrick(trickDTO);
    	trick = trickRepository.saveAndFlush(trick);
    	return trickMapper.trickToTrickDTO(trick);
	}

	 /**
     * Find all the most recent tricks.
     * @return list of entities
     */
	@Override
	public List<TrickDTO> findTheMostLatests() {
		LOGGER.debug("Request to find the lastests tricks");
		return trickRepository.findTheMostLatests().stream()
		.map(trickMapper::trickToTrickDTO).collect(Collectors.toList());
	}

	 /**
     * Find all the most viewed tricks.
     * @return list of entities
     */
	@Override
	public List<TrickDTO> findTheMostViewed() {
		LOGGER.debug("Request to find the most viewed tricks");
		return trickRepository.findTheMostViewed().stream()
				.map(trickMapper::trickToTrickDTO).collect(Collectors.toList());
	}

	/**
     * Add view to trick according to trickId
     * @param trickId : the id of trick.
     * @return the entity
     */
	@Override
	public TrickDTO addViewToTrick(Long trickId) {
		LOGGER.debug("Request to add view to a trick: {}", trickId);
		final Optional<Trick> trick = trickRepository.findById(trickId);
		if (trick.isPresent()) {
			Trick aTrick = trick.get();
			aTrick.setViewNumber(aTrick.getViewNumber() + 1);
			aTrick = trickRepository.saveAndFlush(aTrick);
			return trickMapper.trickToTrickDTO(aTrick);
		}
		return null;
	}
	

}
