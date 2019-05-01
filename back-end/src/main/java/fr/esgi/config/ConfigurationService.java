package fr.esgi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * ConfigurationService for managing configuration properties.
 * @author christopher
 */
@Configuration
public class ConfigurationService {

    @Value("#{'${app.authorizedURLs}'.split(',')}")
    private Set<String> corsAllowedOrigins;

    @Value("${app.mail.host}")
    private String host;

    @Value("${app.mail.port}")
    private int port;

    @Value("${app.mail.username}")
    private String username;

    @Value("${app.mail.password}")
    private String password;

    @Value("#{'${app.mail.recipients}'.split(',')}")
    private Set<String> recipients;

    public Set<String> getCorsAllowedOrigins() {
        return corsAllowedOrigins;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRecipients() {
        return recipients;
    }
}
