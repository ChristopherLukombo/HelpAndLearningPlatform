package fr.esgi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 *  Configuration for configure MailConfig.
 */
@Configuration
public class MailConfig {

    private final ConfigurationService configurationService;

    @Autowired
    public MailConfig(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(configurationService.getHost());
        mailSender.setPort(configurationService.getPort());
        mailSender.setUsername(configurationService.getUsername());
        mailSender.setPassword(configurationService.getPassword());

        final Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");

        return mailSender;
    }

}
