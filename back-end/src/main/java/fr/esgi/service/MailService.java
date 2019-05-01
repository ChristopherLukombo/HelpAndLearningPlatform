package fr.esgi.service;

import fr.esgi.exception.HelpAndLearningPlatformException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service Interface for managing Mail.
 */
@Service
public interface MailService {

    /**
     * Send mail to a recipient.
     * @param to sender of mail
     * @param subject of mail
     * @param body of mail
     * @throws HelpAndLearningPlatformException if there an error during mail
     */
    void send(Set<String> to, String subject, String body) throws HelpAndLearningPlatformException;
}
