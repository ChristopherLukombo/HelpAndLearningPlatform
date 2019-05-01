package fr.esgi.service.impl;

import fr.esgi.dao.NotationRepository;
import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.dao.TrickRepository;
import fr.esgi.domain.Notation;
import fr.esgi.domain.Trick;
import fr.esgi.service.StatsService;
import fr.esgi.service.dto.StatsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Stats.
 */
@Service("StatsService")
@Transactional
public class StatsServiceImpl implements StatsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotationServiceImpl.class);

    private final TrickRepository trickRepository;

    private final NotationRepository notationRepository;

    private final SubscriptionRepository subscriptionRepository;


    @Autowired
    public StatsServiceImpl(TrickRepository trickRepository, NotationRepository notationRepository,
                            SubscriptionRepository subscriptionRepository) {
        this.trickRepository = trickRepository;
        this.notationRepository = notationRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
	 * Get the stats for a trick
	 * @param userId the id of user
	 * @param categoryId the id of category
	 * @return the entity
	 */
    @Transactional(readOnly = true)
    @Override
    public StatsDTO getStatsForTrick(Long userId, Long categoryId) {
        LOGGER.debug("request to get stats");
        List<Trick> tricks = trickRepository.findAllByOwnUserId(userId);

        if (null == tricks || tricks.isEmpty()) {
            return null;
        }

        final List<Notation> notations = new ArrayList<>();

        for (Trick trick : tricks) {
            notations.addAll(notationRepository.findAllByTrickId(trick.getId()));
        }

        Optional<Double> sum = calculateMark(notations);

        final StatsDTO statsDTO = new StatsDTO();
        statsDTO.setMark((sum.isPresent() && sum.get() > 0) ? sum.get() / notations.size() : 0);
        statsDTO.getNumberOfSubscribedUsers();
        statsDTO.setNumberOfSubscribedUsers(subscriptionRepository.findNumberSubscriptionsByCategoryId(categoryId));

        return statsDTO;
    }

    private static Optional<Double> calculateMark(List<Notation> notations) {
        return notations.stream()
                .map(Notation::getNote)
                .reduce(Double::sum);
    }
}
