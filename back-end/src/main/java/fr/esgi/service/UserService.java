package fr.esgi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.esgi.domain.User;
import fr.esgi.service.dto.UserDTO;

/**
 * Service Implementation for managing User.
 */
@Service
public interface UserService {

	/**
	 * Add a friend to a user.
	 * @param userDTO the entity user
	 * @param friendDTO the friend to add to a user
	 * @return entity
	 */
	UserDTO addFriend(UserDTO userDTO, UserDTO friendDTO);

	/**
	 * Save the user in database.
	 * @param userDTO the entity to save
	 * @param password the password of entity
	 * @return UserDTO the persisted entity
	 */
	UserDTO registerUser(UserDTO userDTO, String password);
	
	/**
	 * Returns user by id.
	 * @param id of the user
	 * @return the entity
	 */
	Optional<UserDTO> findUserById(Long id);

	/**
	 * Returns user by login.
	 * @param login of the user
	 * @return the entity
	 */
	Optional<User> findUserByLogin(String login);

	/**
	 * Returns user by email.
	 * @param email of the user
	 * @return the entity
	 */
	Optional<User> findUserByEmail(String email);

	/**
	 * Returns all users.
	 * @return the list of entities
	 */
	List<UserDTO> findAll();

	/**
	 * Returns User by username.
	 * @param username : login or email
	 * @return the entity
	 */
	Optional<UserDTO> findUserByUsername(String username);

	/**
	 * Update a user.
	 * @param userDTO : the entity to update. 
	 * @param password : the new password
	 * @return the entity updated
	 */
	UserDTO update(UserDTO userDTO, String password);
}
