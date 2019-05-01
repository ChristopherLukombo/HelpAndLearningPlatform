package fr.esgi.service.impl;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

/**
 * Service Implementation for managing Mail.
 */
@Service("MailService")
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Send mail to a recipient.
     * @param to sender of mail
     * @param subject of mail
     * @param body of mail
     * @throws HelpAndLearningPlatformException if there an error during mail
     */
    @Override
    public void send(Set<String> to, String subject, String body) throws HelpAndLearningPlatformException {
        try {
            LOGGER.debug("Request to send mail to: {}", to);
            final SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(Arrays.toString(to.toArray()));
            message.setSubject(subject);
            message.setText(body);

            this.javaMailSender.send(message);
        } catch (MailException e) {
            LOGGER.error("Error during sending mail to: {}", to, e);
            throw new HelpAndLearningPlatformException("Error during sending mail to: {}", e);
        }
    }
}
