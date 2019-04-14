package fr.esgi.web.rest;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.UserService;
import fr.esgi.service.dto.UserDTO;
import fr.esgi.service.mapper.UserMapper;
import fr.esgi.web.ManagedUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * REST controller for managing the current user's account.
 * @author christopher
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountResource.class);

    private final UserService userService;


    @Autowired
    public AccountResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST  /register : register the user.
     *
     * @param managedUser the managed user View Model
     */
    @PostMapping("/register")
    public ResponseEntity registerAccount(
            @RequestBody @Valid ManagedUser managedUser
    ) throws HelpAndLearningPlatformException, URISyntaxException {
        if (!checkPasswordLength(managedUser.getPassword())) {
            throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
                    "Password is not valid.");
        }

        if (userService.loginIsPresent(managedUser)) {
            throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
                   "Login is already registered.");
        }

        if (userService.emailIsPresent(managedUser)) {
            throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
                    "Email is already used.");
        }

        UserDTO userDTO = userService.registerUser(managedUser, managedUser.getPassword());

        return ResponseEntity.created(new URI("/api/users/" + userDTO.getId()))
                .build();
    }


    /**
     * GET  /authenticate : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request
     * @return the login if the user is authenticated
     */
    @GetMapping("/authenticate")
    public ResponseEntity<String> isAuthenticated(HttpServletRequest request) {
        LOGGER.debug("REST request to check if the current user is authenticated");
        return new ResponseEntity<>(request.getRemoteUser(), HttpStatus.OK);
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= ManagedUser.PASSWORD_MIN_LENGTH &&
                password.length() <= ManagedUser.PASSWORD_MAX_LENGTH;
    }

}
