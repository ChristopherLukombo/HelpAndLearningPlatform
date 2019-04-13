package fr.esgi.web.rest;

import fr.esgi.domain.Subscription;
import fr.esgi.service.SubscriptionService;
import fr.esgi.service.dto.SubscriptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class SubscriptionResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionResource.class);

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionResource(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * POST  /subscriptions : abonnement à un cours
     *
     * @return the ResponseEntity with status 201 (OK) and the subscription in body
     */
    public ResponseEntity<Subscription> saveSubscription(@RequestBody @Valid SubscriptionDTO subscriptionDTO) throws URISyntaxException {
        SubscriptionDTO subscription = subscriptionService.saveSubscription(subscriptionDTO);
        return ResponseEntity.created(new URI("/api/subscription/" + subscription.getId()))
                .build();
    }
}
