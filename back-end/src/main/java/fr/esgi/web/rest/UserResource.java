package fr.esgi.web.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.UserService;
import fr.esgi.service.dto.UserDTO;
import io.swagger.annotations.Api;

/**
 * REST controller for managing User.
 * @author christopher
 */
@Api(value = "User")
@RestController
@RequestMapping("/api")
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * PATCH  /users : add a friend to an user
     * @param userDTO the user
     * @param friendDTO the friend to add
     * @return the ResponseEntity with status 200 (OK)
     */
    @PatchMapping(value = "/users/friend")
    public ResponseEntity<UserDTO> addFriend(@RequestBody @Valid UserDTO userDTO, @RequestBody @Valid UserDTO friendDTO) throws HelpAndLearningPlatformException {
        LOGGER.debug("REST request to add a friend to an user: {}, {}", userDTO, friendDTO);
        if (null == userDTO.getId() || null == friendDTO.getId()) {
            throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
                    "A user cannot have an empty ID");
        }
        final UserDTO user = userService.addFriend(userDTO, friendDTO);
        return ResponseEntity.ok().body(user);
    }
}
