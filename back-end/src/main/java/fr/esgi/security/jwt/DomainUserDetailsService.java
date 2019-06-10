package fr.esgi.security.jwt;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.dao.UserRepository;
import fr.esgi.domain.User;
import fr.esgi.exception.HelpAndLearningPlatformException;

/**
 * Authenticate a user from the database.
 * @author christopher
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;
  
    @Autowired  
    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        LOGGER.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<User> userByEmailFromDatabase = userRepository.findOneByEmailIgnoreCase(lowercaseLogin);
        return userByEmailFromDatabase.map(user -> {
            try {
                return getUser(lowercaseLogin, user);
            } catch (HelpAndLearningPlatformException e) {
                return null;
            }
        }).orElseGet(() -> {
            Optional<User> userByLoginFromDatabase = userRepository.findOneByLoginIgnoreCase(lowercaseLogin);
            return userByLoginFromDatabase.map(user -> {
                try {
                    return getUser(lowercaseLogin, user);
                } catch (HelpAndLearningPlatformException e) {
                    return null;
                }
            })
                    .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
                            "database"));
        });
    }

    private org.springframework.security.core.userdetails.User getUser(String lowercaseLogin, User user) throws HelpAndLearningPlatformException {
        org.springframework.security.core.userdetails.User springSecurityUser = createSpringSecurityUser(lowercaseLogin, user);
        updateLastConnection(user);
        return springSecurityUser;
    }

    private void updateLastConnection(User user) {
        user.setDateOfLastConnection(ZonedDateTime.now(ZoneId.of("Europe/Paris")));
        userRepository.saveAndFlush(user);
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) throws HelpAndLearningPlatformException {
        if (!user.isActivated()) {
            throw new HelpAndLearningPlatformException("User " + lowercaseLogin + " was not activated");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority((null != user.getAuthority()) ? user.getAuthority().getName() : "ROLE"));

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                grantedAuthorities);
    }
}
