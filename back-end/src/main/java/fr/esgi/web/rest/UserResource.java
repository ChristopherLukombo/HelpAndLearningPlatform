package fr.esgi.web.rest;

import fr.esgi.service.UserService;
import fr.esgi.service.dto.UserDTO;
import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     * @param userDTO 
     * @param friendDTO 
     *
     * @return the ResponseEntity with status 200 (OK)
     */
    @PatchMapping(value = "/users")
    public ResponseEntity<Object> createNotation(@RequestBody @Valid UserDTO userDTO, @RequestBody @Valid UserDTO friendDTO) {
        LOGGER.debug("REST request to add a friend to an user: {}, {}", userDTO, friendDTO);

        userService.addFriend(userDTO, friendDTO);

        return ResponseEntity.ok().build();
    }
}
