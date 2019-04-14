package fr.esgi.service.impl;

import fr.esgi.dao.NotationRepository;
import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.dao.TrickRepository;
import fr.esgi.domain.Notation;
import fr.esgi.domain.Trick;
import fr.esgi.service.StatsService;
import fr.esgi.service.dto.StatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("StatsService")
public class StatsServiceImpl implements StatsService {

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

    @Override
    public StatsDTO getStatsForTrick(Long userId, Long categoryId) {
        List<Trick> tricks = trickRepository.findAllByOwnUser(userId);

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
        statsDTO.setNumberOfSubscribedUsers(subscriptionRepository.findNumberSubscriptions(categoryId));

        return statsDTO;
    }

    private static Optional<Double> calculateMark(List<Notation> notations) {
        return notations.stream()
                .map(Notation::getNote)
                .reduce(Double::sum);
    }
}
