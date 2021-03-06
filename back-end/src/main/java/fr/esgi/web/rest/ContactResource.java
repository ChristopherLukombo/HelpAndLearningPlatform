package fr.esgi.web.rest;

import fr.esgi.config.ErrorMessage;
import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.ContactService;
import fr.esgi.service.dto.ContactDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
     * POST  /contacts : contact Company in sending a mail.
     * @param contactDTO the contactDTO to contact
     * @return the ResponseEntity with status 200 (OK)
     * @throws HelpAndLearningPlatformException if there an error during contact Company
     */
    @ApiOperation(value = "Contact Company in sending a mail.")
    @PostMapping(value = "/contacts")
    public ResponseEntity<ContactDTO> contactCompany(@RequestBody @Valid ContactDTO contactDTO) throws HelpAndLearningPlatformException {
        LOGGER.debug("REST request to contact Company: {}", contactDTO);
        ContactDTO result = null;
        try {
        	result = contactService.contact(contactDTO);
        } catch (HelpAndLearningPlatformException e) {
            throw new HelpAndLearningPlatformException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ErrorMessage.ERROR_TRYING_TO_CONTACT_COMPANY, e);
        }
        return ResponseEntity.ok().body(result);
    }
}
