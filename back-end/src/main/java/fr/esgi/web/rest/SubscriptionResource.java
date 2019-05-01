package fr.esgi.web.rest;

import fr.esgi.domain.Subscription;
import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.SubscriptionService;
import fr.esgi.service.dto.SubscriptionDTO;
import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * REST controller for managing Subscription.
 * @author christopher
 */
@Api(value = "Subscription")
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
     * POST  /subscriptions : course subscription
     * @param subscriptionDTO the assignmentModuleDTO to create
     * @return the ResponseEntity with status 201 (OK) and the subscription in body
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/subscriptions")
    public ResponseEntity<Subscription> createSubscription(@RequestBody @Valid SubscriptionDTO subscriptionDTO) throws URISyntaxException, HelpAndLearningPlatformException {
        LOGGER.debug("REST request to save Subscription: {}", subscriptionDTO);
        if (null != subscriptionDTO.getId()) {
            throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
                    "A new subscription cannot already have an ID");
        }
        SubscriptionDTO subscription = subscriptionService.saveSubscription(subscriptionDTO);
        return ResponseEntity.created(new URI("/api/subscription/" + subscription.getId()))
                .build();
    }
}
