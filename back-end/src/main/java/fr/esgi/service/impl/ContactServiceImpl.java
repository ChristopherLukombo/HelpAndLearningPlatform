package fr.esgi.service.impl;

import fr.esgi.config.ConfigurationService;
import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.ContactService;
import fr.esgi.service.MailService;
import fr.esgi.service.dto.ContactDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing Contact.
 */
@Service("ContactService")
public class ContactServiceImpl implements ContactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final MailService mailService;

    private final ConfigurationService configurationService;

    @Autowired
    public ContactServiceImpl(MailService mailService, ConfigurationService configurationService) {
        this.mailService = mailService;
        this.configurationService = configurationService;
    }

    /**
     * Contact a company.
     *
     * @param contactDTO the contactDTO to contact
     * @throws HelpAndLearningPlatformException if there is an error
     */
    @Override
    public ContactDTO contact(ContactDTO contactDTO) throws HelpAndLearningPlatformException {
    	ContactDTO result = null;
        try {
            LOGGER.debug("Contact Company: {}", contactDTO);
            mailService.send(
                    configurationService.getRecipients(), contactDTO.getSubject(), contactDTO.getInformation()
            );
            result = contactDTO;
        } catch (HelpAndLearningPlatformException e) {
            throw new HelpAndLearningPlatformException("Error during sending mail", e);
        }
        return result;
    }
}
