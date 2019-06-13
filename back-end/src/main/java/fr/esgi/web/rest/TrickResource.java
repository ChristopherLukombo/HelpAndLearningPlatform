package fr.esgi.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.TrickService;
import fr.esgi.service.dto.TrickDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

    /**
     * GET  /tricks : returns all tricks.
     * @return the list of entities
     * @throws HelpAndLearningPlatformException if there is no tricks.
     */
    @ApiOperation(value = "Returns all tricks.")
    @GetMapping("/tricks")
    public ResponseEntity<List<TrickDTO>> getAllTricks() throws HelpAndLearningPlatformException {
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
     * GET  /tricks/finished : Get all tricks with status and by user id.
     * @param userId the userId of user
     * @return the ResponseEntity with status 200 (OK) and the list of entities in body.
     * @throws HelpAndLearningPlatformException if there is no tricks.
     */
    @ApiOperation(value = "Get all tricks with status and by user id.")
    @GetMapping("/tricks/finished")
    public ResponseEntity<List<TrickDTO>> getAllTrickFinishedByUserId(
    		@RequestParam Long userId,
    		@ApiParam(value = "true or false for value", required = true) @RequestParam Boolean finished
    		) throws HelpAndLearningPlatformException {
    	LOGGER.debug("REST request to find all new tricks available: {} {}", userId, finished);
    	final List<TrickDTO> tricksDTO = trickService.findAllByUserIdAndStatus(userId, finished);
    	if (tricksDTO.isEmpty()) {
    		throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), 
    				"Pas de tricks");
    	}
    	return ResponseEntity.ok()
    			.body(tricksDTO);
    }
    
    /**
     * GET  /tricks/news/{userId} : get all new tricks available by user id
     * @param userId the userId of user
     * @return the ResponseEntity with status 200 (OK) and the list of entities in body.
     * @throws HelpAndLearningPlatformException if there is no tricks.
     */
    @ApiOperation(value = "Get all new tricks available by user id.")
    @GetMapping("/tricks/news/{userId}")
    public ResponseEntity<List<TrickDTO>> getAllNewTricksAvailableByUserId(@PathVariable Long userId) throws HelpAndLearningPlatformException {
        LOGGER.debug("REST request to find all new tricks available: {}", userId);
        List<TrickDTO> tricksDTO = trickService.findAllNewTricksAvailableByUserId(userId);
        if (tricksDTO.isEmpty()) {
           	throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), 
        			"Pas de tricks");
        }
		return ResponseEntity.ok()
                .body(tricksDTO);
    }
    
    /**
     * GET  /tricks/news/{userId} : get all the most recent tricks.
     * @param userId the userId of user
     * @return the ResponseEntity with status 200 (OK) and the list of entities in body.
     * @throws HelpAndLearningPlatformException if there is no tricks.
     */
    @ApiOperation(value = "Get all the most recent tricks.")
    @GetMapping("/tricks/mostlatests")
    public ResponseEntity<List<TrickDTO>> getTheMostLatests() throws HelpAndLearningPlatformException {
        LOGGER.debug("REST request to get all the most recent tricks");
        List<TrickDTO> tricksDTO = trickService.findTheMostLatests();
        if (tricksDTO.isEmpty()) {
           	throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), 
        			"Pas de tricks");
        }
		return ResponseEntity.ok()
                .body(tricksDTO);
    }
    
    /**
     * GET  /tricks/news/{userId} : get all the most viewed tricks.
     * @return the ResponseEntity with status 200 (OK) and the list of entities in body.
     * @throws HelpAndLearningPlatformException if there is no tricks.
     */
    @ApiOperation(value = "Get all the most viewed tricks.")
    @GetMapping("/tricks/mostviewed")
    public ResponseEntity<List<TrickDTO>> getTheMostViewed() throws HelpAndLearningPlatformException {
    	LOGGER.debug("REST request to find all the most viewed tricks");
    	List<TrickDTO> tricksDTO = trickService.findTheMostViewed();
    	if (tricksDTO.isEmpty()) {
    		throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), 
    				"Pas de tricks");
    	}
    	return ResponseEntity.ok()
    			.body(tricksDTO);
    }
    
    /**
     * PUT /tricks : update a trick by its entity.
     * @param trickDTO the entity trick
     * @return the ResponseEntity with status 200 (OK) and the entity in the body.
     * @throws HelpAndLearningPlatformException if id of trick is null.
     */
    @ApiOperation(value = "Update a trick by its entity.")
    @PutMapping("/tricks")
    public ResponseEntity<TrickDTO> updateTrick(@RequestBody TrickDTO trickDTO) throws HelpAndLearningPlatformException {
    	LOGGER.debug("REST request to update a trick: {}", trickDTO);
    	if (null == trickDTO.getId()) {
    		throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(), 
    				"Erreur l'id du trick ne peut être nul !");
    	}
    	final TrickDTO trick = trickService.update(trickDTO);
    	return ResponseEntity.ok()
    			.body(trick);

    }
    
    /**
     * PATCH /tricks : Add view to trick according to trickId
     * @param trickId : the id of trick.
     * @return the ResponseEntity with status 200 (OK) and the entity in the body.
     */
    @ApiOperation(value = "Add view to trick according to trickId")
    @PatchMapping("tricks/addView/{trickId}")
    public ResponseEntity<TrickDTO> addViewToTrick(@PathVariable Long trickId) {
    	LOGGER.debug("REST request to add view to a trick by trickId: {}", trickId);
    	TrickDTO trickDTO = trickService.addViewToTrick(trickId);
    	return ResponseEntity.ok()
    			.body(trickDTO);
    }
    
}
