package fr.esgi.service;

import fr.esgi.service.dto.SubscriptionDTO;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService {

    SubscriptionDTO saveSubscription(SubscriptionDTO subscriptionDTO);
}
