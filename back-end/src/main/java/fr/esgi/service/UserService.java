package fr.esgi.service;

import fr.esgi.service.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

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
