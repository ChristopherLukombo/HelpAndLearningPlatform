package fr.esgi.web.rest;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.ContactService;
import fr.esgi.service.dto.ContactDTO;
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

/**
 * REST controller for managing Contact.
 */
@Api(value = "Contact")
@RestController
@RequestMapping("/api")
public class ContactResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactResource.class);

    private final ContactService contactService;

    @Autowired
    public ContactResource(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * POST  /contacts : contact Company
     * @param contactDTO the contactDTO to contact
     * @return the ResponseEntity with status 200 (OK)
     * @throws HelpAndLearningPlatformException if there an error during contact Company
     */
    @PostMapping(value = "/contacts")
    public ResponseEntity<Void> contactCompany(@RequestBody @Valid ContactDTO contactDTO) throws HelpAndLearningPlatformException {
        LOGGER.debug("REST request to contact Company: {}", contactDTO);
        try {
            contactService.contact(contactDTO);
        } catch (HelpAndLearningPlatformException e) {
            LOGGER.error("Error trying to contact Company", e);
            throw new HelpAndLearningPlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error trying to contact Company", e);
        }
        return ResponseEntity.ok().build();
    }
}
