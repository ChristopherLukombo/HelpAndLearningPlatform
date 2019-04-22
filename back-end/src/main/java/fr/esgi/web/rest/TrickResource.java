package fr.esgi.web.rest;

import fr.esgi.service.TrickService;
import fr.esgi.service.dto.TrickDTO;
import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
     * GET  /tricks : get all new tricks availaible by user id
     * @param userId 
     *
     * @return the ResponseEntity with status 200 (OK) and the list of entities in body.
     */
    @GetMapping("/tricks/{userId}")
    public List<TrickDTO> getAllNewTricksAvailableByUserId(@PathVariable Long userId) {
        LOGGER.debug("REST request to find all new tricks available: {}", userId);

        return trickService.findAllNewTricksAvailableByUserId(userId);
    }


}
