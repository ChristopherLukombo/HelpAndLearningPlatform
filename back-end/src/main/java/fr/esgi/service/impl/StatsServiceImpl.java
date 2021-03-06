package fr.esgi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.dao.CommentRepository;
import fr.esgi.dao.NotationRepository;
import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.dao.TrickRepository;
import fr.esgi.domain.Comment;
import fr.esgi.domain.Notation;
import fr.esgi.domain.Trick;
import fr.esgi.service.StatsService;
import fr.esgi.service.dto.StatsDTO;
import fr.esgi.service.dto.StatsTrickDTO;

/**
 * Service Implementation for managing Stats.
 */
@Service("StatsService")
@Transactional
public class StatsServiceImpl implements StatsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatsServiceImpl.class);

    private final TrickRepository trickRepository;

    private final NotationRepository notationRepository;

    private final SubscriptionRepository subscriptionRepository;
    
    private final CommentRepository commentRepository;
   
    @Autowired
    public StatsServiceImpl(TrickRepository trickRepository, NotationRepository notationRepository,
			SubscriptionRepository subscriptionRepository, CommentRepository commentRepository) {
		this.trickRepository = trickRepository;
		this.notationRepository = notationRepository;
		this.subscriptionRepository = subscriptionRepository;
		this.commentRepository = commentRepository;
	}

	/**
	 * Get the stats for a trick
	 * @param userId the id of user
	 * @param categoryId the id of category
	 * @return the entity
	 */
    @Override
    @Transactional(readOnly = true)
    public StatsDTO getStatsTricksForOwner(Long userId, Long categoryId) {
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
        statsDTO.setNumberOfSubscribedUsers(subscriptionRepository.findNumberSubscriptionsByCategoryId(categoryId));

        return statsDTO;
    }

    private static Optional<Double> calculateMark(List<Notation> notations) {
        return notations.stream()
                .map(Notation::getNote)
                .reduce(Double::sum);
    }

    /**
     * Get stat for a trick according trickId.
     * @param trickId
     * @return the entity stats 
     */
	@Override
    @Transactional(readOnly = true)
	public StatsTrickDTO getStatsForTrick(Long trickId) {
		LOGGER.debug("request to get stats for a trick: {}", trickId);
        final List<Notation> notations = notationRepository.findAllByTrickId(trickId);
        final List<Comment> comments = commentRepository.findAllByTrickId(trickId);
        Integer numberSubcriber = subscriptionRepository.findNumberSubcriber(trickId);
        
        final Optional<Double> sum = calculateMark(notations);

        final StatsTrickDTO statsTrickDTO = new StatsTrickDTO();
        statsTrickDTO.setMark((sum.isPresent() && sum.get() > 0) ? sum.get() / notations.size() : 0);
        statsTrickDTO.setNumberOfComments((null != comments && !comments.isEmpty()) ? comments.size() : 0);
        statsTrickDTO.setNumberOfSubscribedUsers((null != numberSubcriber) ? numberSubcriber : 0);

        return statsTrickDTO;
	}
}
