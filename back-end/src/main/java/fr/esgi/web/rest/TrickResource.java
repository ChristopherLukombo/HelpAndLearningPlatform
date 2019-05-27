package fr.esgi.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.TrickService;
import fr.esgi.service.dto.TrickDTO;
import io.swagger.annotations.Api;

/**
 * REST controller for managing Trick.
 * @author christopher
 */
@Api(value = "Trick")
@RestController
@RequestMapping("/api")
public class TrickResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrickResource.class);

    private final TrickService trickService;

    @Autowired
    public TrickResource(TrickService trickService) {
        this.trickService = trickService;
    }

    @GetMapping("/tricks")
    public ResponseEntity<List<TrickDTO>> getAll() throws HelpAndLearningPlatformException {
        LOGGER.debug("REST request to find all new tricks");
        final List<TrickDTO> tricksDTO = trickService.findAll();
        if (tricksDTO.isEmpty()) {
           	throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), 
        			"Pas de tricks");
        }
        return ResponseEntity.ok()
                .body(tricksDTO);
    }
    
    /**
     * GET  /tricks/{userId} : get all new tricks available by user id
     * @param userId the userId of user
     * @return the ResponseEntity with status 200 (OK) and the list of entities in body.
     */
    @GetMapping("/tricks/{userId}")
    public ResponseEntity<List<TrickDTO>> getAllNewTricksAvailableByUserId(@PathVariable Long userId) {
        LOGGER.debug("REST request to find all new tricks available: {}", userId);
        return ResponseEntity.ok()
                .body(trickService.findAllNewTricksAvailableByUserId(userId));
    }
    
    /**
     * PUT /tricks : update a trick by its entity.
     * @param trickDTO the entity trick
     * @return the ResponseEntity with status 200 (OK) and the entity in the body.
     * @throws HelpAndLearningPlatformException
     */
    @PutMapping("/tricks")
    public ResponseEntity<TrickDTO> updateTrick(@RequestBody TrickDTO trickDTO) throws HelpAndLearningPlatformException {
    	 LOGGER.debug("REST request to update a trick: {}", trickDTO);
        if (null == trickDTO.getId()) {
        	throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(), 
        			"Erreur l'id du trick ne peut être nul !");
        }
    	return ResponseEntity.ok()
    			.body(trickService.update(trickDTO));
    	
    }

}
