package fr.esgi.service.impl;

import fr.esgi.dao.UserRepository;
import fr.esgi.domain.User;
import fr.esgi.service.UserService;
import fr.esgi.service.dto.UserDTO;
import fr.esgi.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void addFriend(UserDTO userDTO, UserDTO friendToAdd) {
        User user = userMapper.userDTOToUser(userDTO);

        User friend = userMapper.userDTOToUser(friendToAdd);

        user.addFriends(Arrays.asList(friend));

        userRepository.saveAndFlush(user);
    }

    /**
     * Save the user in database.
     * @param userDTO
     * @param password
     * @return UserDTO
     */
    public UserDTO registerUser(UserDTO userDTO, String password) {
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setImageUrl(userDTO.getImageUrl());
        newUser.setCountryOfResidence(userDTO.getCountryOfResidence());
        // new user is active
        newUser.setActivated(true);
        newUser = userRepository.save(newUser);

        LOGGER.debug("Created Information for User: {}", newUser);
        return userMapper.userToUserDTO(newUser);
    }

    /**
     * Returns true if the login is already used.
     * @param userDTO
     * @return boolean
     */
    @Transactional(readOnly = true)
    public boolean loginIsPresent(UserDTO userDTO) {
        return userRepository.findOneByLogin(userDTO.getLogin().toLowerCase())
                .isPresent();
    }

    /**
     * Returns true if the email is already used.
     * @param userDTO
     * @return boolean
     */
    @Transactional(readOnly = true)
    public boolean emailIsPresent(UserDTO userDTO) {
        return userRepository.findOneByEmailIgnoreCase(userDTO.getEmail())
                .isPresent();
    }

}
