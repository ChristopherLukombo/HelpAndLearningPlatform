package fr.esgi.web.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.UserService;
import fr.esgi.service.dto.UserDTO;
import fr.esgi.web.ManagedUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	@ApiOperation(value = "Add a friend to an user.")
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
	
	/**
	 * GET  /users : returns user by id
	 * @return the list of entities
	 * @throws HelpAndLearningPlatformException 
	 */
	@ApiOperation(value = "Returns user by id.")
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) throws HelpAndLearningPlatformException {
		LOGGER.debug("REST request to get user by id");
		final Optional<UserDTO> userDTO = userService.findUserById(id);
		if (userDTO.isPresent()) {
			return ResponseEntity.ok().body(userDTO.get());
		} else {
			throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(),
					"User does not exist with its id");
		}
	}

	/**
	 * GET  /users : returns all users
	 * @return the list of entities
	 */
	@ApiOperation(value = "Returns all users.")
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		LOGGER.debug("REST request to get all users");
		final List<UserDTO> users = userService.findAll();
		return ResponseEntity.ok().body(users);
	}

	/**
	 * Update a user.
	 * @param managedUser: the entity to update. 
	 * @return the UserDTO updated.
	 * @throws HelpAndLearningPlatformException if the id is empty.
	 */
	@ApiOperation(value = "Update a user.")
	@PutMapping("/users")
	public ResponseEntity<UserDTO> updateUser(@RequestBody ManagedUser managedUser) throws HelpAndLearningPlatformException {
		LOGGER.debug("REST request to update a user: {}", managedUser);
		if (null == managedUser.getId()) {
			throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
					"A user cannot have an empty ID");
		}
		final UserDTO user = userService.update(managedUser, managedUser.getPassword());
		return ResponseEntity.ok().body(user);
	}
}
