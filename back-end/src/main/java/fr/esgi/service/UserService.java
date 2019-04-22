package fr.esgi.service;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.UserDTO;

@Service
public interface UserService {

	/**
     * Add a friend to a user.
     * @param userDTO
	 * @param friendToAdd 
     */
    void addFriend(UserDTO userDTO, UserDTO friendToAdd);

    UserDTO registerUser(UserDTO userDTO, String password);

    /**
     * Returns true if the login is already used.
     * @param userDTO
     * @return boolean
     */
    boolean loginIsPresent(UserDTO userDTO);

    /**
     * Returns true if the email is already used.
     * @param userDTO
     * @return boolean
     */
    boolean emailIsPresent(UserDTO userDTO);

}
