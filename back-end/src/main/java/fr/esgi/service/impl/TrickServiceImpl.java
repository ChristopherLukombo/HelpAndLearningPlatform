package fr.esgi.service.impl;

import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.dao.TrickRepository;
import fr.esgi.domain.Subscription;
import fr.esgi.domain.Trick;
import fr.esgi.service.TrickService;
import fr.esgi.service.dto.TrickDTO;
import fr.esgi.service.mapper.TrickMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("TrickService")
public class TrickServiceImpl implements TrickService {

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
     * Find all new tricks which are available according to the id
	 * @param userId 
	 * @return list of entities
     */
    @Transactional
    @Override
    public List<TrickDTO> findAllNewTricksAvailableByUserId(Long userId) {
        final List<Subscription> subscriptions = subscriptionRepository.findAllByUser(userId);

        if (null == subscriptions || subscriptions.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Trick> allNewTricksAvailable = new ArrayList<>();

        final LocalDate dateBefore = getDateBefore();

        for (Subscription subscription : subscriptions) {
            allNewTricksAvailable.addAll(
                    trickRepository.findAllNewTricksAvailable(dateBefore, subscription.getCategory().getId()));
        }

        return allNewTricksAvailable.stream()
                .map(trickMapper::trickToTrickDTO)
                .collect(Collectors.toList());
    }

    private LocalDate getDateBefore() {
        final LocalDate today = LocalDate.now();
        return today.minusDays(2);
    }
}
