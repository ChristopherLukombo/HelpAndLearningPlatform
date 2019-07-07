package fr.esgi.service;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.dto.ContactDTO;
import org.springframework.stereotype.Service;

/**
 * Service Interface for managing Contact.
 */
@Service
public interface ContactService {

    /**
     * Contact a company.
     *
     * @param contactDTO the contactDTO to contact
     * @throws HelpAndLearningPlatformException if there is an error
     */
	ContactDTO contact(ContactDTO contactDTO) throws HelpAndLearningPlatformException;
}
