package fr.esgi.service.impl;

import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.domain.Subscription;
import fr.esgi.service.SubscriptionService;
import fr.esgi.service.dto.SubscriptionDTO;
import fr.esgi.service.mapper.SubscriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("SubscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Transactional
    public SubscriptionDTO saveSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionMapper.subscriptionDTOToSubscription(subscriptionDTO);
        subscription = subscriptionRepository.save(subscription);

        return subscriptionMapper.subscriptionToSubscriptionDTO(subscription);
    }
}
