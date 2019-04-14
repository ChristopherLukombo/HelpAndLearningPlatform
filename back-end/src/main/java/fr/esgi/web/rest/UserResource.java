package fr.esgi.web.rest;

import fr.esgi.service.UserService;
import fr.esgi.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
     *
     * @return the ResponseEntity with status 200 (OK)
     */
    @PostMapping(value = "/users")
    public ResponseEntity createNotation(@RequestBody @Valid UserDTO userDTO, UserDTO friendDTO) {
        LOGGER.debug("REST request to add a friend to an user: {}, {}", userDTO, friendDTO);

        userService.addFriend(userDTO, friendDTO);

        return ResponseEntity.ok().build();
    }
}
