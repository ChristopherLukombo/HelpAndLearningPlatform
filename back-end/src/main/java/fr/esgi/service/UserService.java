package fr.esgi.service;

import fr.esgi.service.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void addFriend(UserDTO userDTO, UserDTO friendToAdd);
}
