package fr.esgi.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.SubscriptionService;
import fr.esgi.service.dto.SubscriptionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	 * POST  /subscriptions : create a subscription
	 * @param subscriptionDTO : the subscriptionDTO to create
	 * @return the ResponseEntity with status 201 (OK) and the subscription in body
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@ApiOperation(value = "Create a subscription.")
	@PostMapping("/subscriptions")
	public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody @Valid SubscriptionDTO subscriptionDTO) throws URISyntaxException, HelpAndLearningPlatformException {
		LOGGER.debug("REST request to save Subscription: {}", subscriptionDTO);
		if (null != subscriptionDTO.getId()) {
			throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
					"A new subscription cannot already have an ID");
		}
		if (subscriptionService.isSubscribed(subscriptionDTO)) {
			throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
					"Trick is already subscribed by the user");
		}
		final SubscriptionDTO subscription = subscriptionService.save(subscriptionDTO);
		return ResponseEntity.created(new URI("/api/subscription/" + subscription.getId()))
				.body(subscription);
	}

	/**
	 * GET  /subscriptions/{id} : get subscription by its id.
	 * @param subscriptionDTO : the subscriptionDTO to create
	 * @return the ResponseEntity with status 200 (OK) and the subscription in body
	 * @throws HelpAndLearningPlatformException if the subscriptionDTO is not found.
	 */
	@ApiOperation(value = "Get subscription by its id.")
	@GetMapping("/subscriptions/{id}")
	public ResponseEntity<SubscriptionDTO> getSubscription(@PathVariable Long id) throws HelpAndLearningPlatformException {
		LOGGER.debug("REST request to get a Subscription: {}", id);
		Optional<SubscriptionDTO> subscription = subscriptionService.findOne(id);
		if (subscription.isPresent()) {
			return ResponseEntity.ok().body(subscription.get());
		} else {
			throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), "Subscription non trouvé !");
		}
	}

	/**
	 * PATCH  /subscriptions : set a subscription finished to true.
	 * @param subscriptionId : id of the subscription.
	 * @return the ResponseEntity with status 200 (OK) and the subscription in body
	 * @throws HelpAndLearningPlatformException if the fields subscriptionId can be empty 
	 */
	@ApiOperation(value = "Set a subscription finished to true")
	@PatchMapping("/subscriptions/finished")
	public ResponseEntity<SubscriptionDTO> setSubscriptionToFinished(
			@RequestParam("subscriptionId") Long subscriptionId) throws HelpAndLearningPlatformException {
		LOGGER.debug("REST request to set subscription of trick to true: {}", subscriptionId);
		if (null == subscriptionId) {
			throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
					"The field subscriptionId can be empty.");
		}
		SubscriptionDTO subscriptionDTO = subscriptionService.setToFinished(subscriptionId);
		return ResponseEntity.ok().body(subscriptionDTO);
	}

	/**
	 * GET  /subscriptions : get all subscriptions.
	 * @param subscriptionDTO : the subscriptionDTO to create
	 * @return the ResponseEntity with status 200 (OK) and the subscriptions in body
	 * @throws HelpAndLearningPlatformException if the subscriptionDTOs is not found.
	 */
	@ApiOperation(value = "Get all subscriptions.")
	@GetMapping(value = "/subscriptions")
	public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() throws HelpAndLearningPlatformException {
		LOGGER.debug("REST request to get all Subscriptions");
		final List<SubscriptionDTO> subscriptions = subscriptionService.findAll();
		if (subscriptions.isEmpty()) {
			throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), "Pas de subscriptions trouvés !"); 
		}
		return ResponseEntity.ok().body(subscriptions);
	}

	/**
	 * DELETE  /subscriptions/{id} : delete a subscription by its id.
	 * @param id : the id of subscription to delete.
	 * @return the ResponseEntity with status 204 (OK) and nothing in body.
	 */
	@ApiOperation(value = "Delete a subscription by its id.")
	@DeleteMapping("/subscriptions/{id:\\d+}")
	public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
		LOGGER.debug("REST request to delete subscription {}", id);
		subscriptionService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
