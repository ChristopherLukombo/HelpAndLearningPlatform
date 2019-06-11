package fr.esgi.web.rest;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.NotationService;
import fr.esgi.service.dto.NotationDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Notation.
 * @author christopher
 */
@Api(value = "Notation")
@RestController
@RequestMapping("/api")
public class NotationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotationResource.class);

    private final NotationService notationService;

    @Autowired
    public NotationResource(NotationService notationService) {
        this.notationService = notationService;
    }

    /**
     * POST  /notations : save the notation.
     * @param notationDTO the ResponseEntity with status 201 (Created)
     * @return the ResponseEntity with status 201 (OK) and the entity notation in body
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "Save the notation.")
    @PostMapping("/notations")
    public ResponseEntity<NotationDTO> createNotation(@RequestBody @Valid NotationDTO notationDTO) throws URISyntaxException, HelpAndLearningPlatformException {
        LOGGER.debug("REST request to create a notation: {}", notationDTO);
        if (null != notationDTO.getId()) {
            throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
                    "A new notation cannot already have an ID");
        }
        final NotationDTO notation = notationService.save(notationDTO);
        return ResponseEntity.created(new URI("/notations/" + notation.getId()))
        		.body(notation);
    }
    
    /**
     * GET /notations/{id} : get the notation by its id.
     * @param id : id of the notation.
     * @return the ResponseEntity with status 200 (OK) and the entity notation in body
     * @throws HelpAndLearningPlatformException if the notation is not found
     */
    @ApiOperation(value = "Get the notation by its id.")
    @GetMapping("/notations/{id}")
    public ResponseEntity<NotationDTO> getNotation(@PathVariable Long id) throws HelpAndLearningPlatformException {
    	LOGGER.debug("REST request to find a notation: {}", id);
    	final Optional<NotationDTO> notationDTO = notationService.findOne(id);
        if (notationDTO.isPresent()) {
        	return ResponseEntity.ok().body(notationDTO.get());
        } else {
        	throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), "Notation non trouvé");
        }
    }
    
    /**
     * GET  /notations/trick/{trickId} : get the notations according the trickId.
     * @param trickId the trick of notation
     * @return the ResponseEntity with status 200 (OK) and the list of entities notations in body
     * @throws HelpAndLearningPlatformException if the notations are not found
     */
    @ApiOperation(value = "Get the notations according the trickId.")
    @GetMapping("/notations/trick/{trickId}")
    public ResponseEntity<List<NotationDTO>> getAllNotationsByTrickID(@PathVariable Long trickId) throws HelpAndLearningPlatformException {
    	LOGGER.debug("REST request to find all Notation by Trick ID : {}", trickId);
    	final List<NotationDTO> notationsDTO = notationService.findAllByTrickId(trickId);
    	if (notationsDTO.isEmpty()) {
    		throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), "Pas de notations trouvés");
    	}
    	return ResponseEntity.ok().body(notationsDTO);
    }
}
