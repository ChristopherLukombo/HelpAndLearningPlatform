package fr.esgi.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.dao.TrickRepository;
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
		final List<Trick> tricks = trickRepository.findAll();
		if (null == tricks) {
			return Collections.emptyList();
		}
		return tricks.stream()
				.map(trickMapper::trickToTrickDTO).collect(Collectors.toList());
	}

	/**
     * Find all new tricks which are available according to the id
	 * @param userId the id user
	 * @return list of entities
     */
    @Transactional(readOnly = true)
    @Override
    public List<TrickDTO> findAllNewTricksAvailableByUserId(Long userId) {
        LOGGER.debug("Request to get all Subscriptions");
        final List<Subscription> subscriptions = subscriptionRepository.findAllByUserId(userId);
        if (null == subscriptions || subscriptions.isEmpty()) {
            return Collections.emptyList();
        }
        final List<Trick> allNewTricksAvailable = new ArrayList<>();
        final LocalDate dateBefore = getDateBefore();
        for (Subscription subscription : subscriptions) {
            allNewTricksAvailable.addAll(
                    trickRepository.findAllNewTricksAvailableByDateAndCategoryId(dateBefore, subscription.getCategory().getId()));
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
		Trick trick = trickMapper.trickDTOToTrick(trickDTO);
    	trick = trickRepository.saveAndFlush(trick);
    	return trickMapper.trickToTrickDTO(trick);
	}
}
