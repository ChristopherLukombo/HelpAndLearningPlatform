package fr.esgi.service;

import fr.esgi.domain.User;
import org.springframework.stereotype.Service;

import fr.esgi.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

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
	 * Returns true if the login is already used.
	 * @param login of the user
	 * @return the entity
	 */
	Optional<User> findUserByLogin(String login);

	/**
	 * Returns true if the email is already used.
	 * @param email of the user
	 * @return the entity
	 */
	Optional<User> findUserByEmail(String email);
	
	/**
	 * Returns all users.
	 * @return the list of entities
	 */
	List<UserDTO> findAll();

}
