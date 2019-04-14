package fr.esgi.web.rest;

import fr.esgi.service.NotationService;
import fr.esgi.service.dto.NotationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

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
     * POST  /notations : save the notation
     *
     * @return the ResponseEntity with status 201 (OK) and the entity notation in body
     */
    @PostMapping(value = "/notations")
    public ResponseEntity createNotation(@RequestBody @Valid NotationDTO notationDTO) throws URISyntaxException {
        LOGGER.debug("REST request to create a notation: {}", notationDTO);

        final NotationDTO notation = notationService.save(notationDTO);

        return ResponseEntity.created(new URI("/notations/" + notation.getId()))
                .build();
    }
}
