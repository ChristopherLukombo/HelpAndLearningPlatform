package fr.esgi.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.dao.AuthorityRepository;
import fr.esgi.dao.UserRepository;
import fr.esgi.domain.Authority;
import fr.esgi.domain.User;
import fr.esgi.service.UserService;
import fr.esgi.service.dto.UserDTO;
import fr.esgi.service.mapper.UserMapper;

/**
 * Service Implementation for managing User.
 */
@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    
    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
			AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.authorityRepository = authorityRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
     * Add a friend to a user.
     * @param userDTO the entity user
	 * @param friendDTO the friend to add to a user
	 * @return entity
     */
    @Override
    public UserDTO addFriend(UserDTO userDTO, UserDTO friendDTO) {
    	LOGGER.debug("Request to add friend to a user: {} {}",userDTO, friendDTO);
        final User user = userMapper.userDTOToUser(userDTO);
        final User friend = userMapper.userDTOToUser(friendDTO);
        user.addFriends(Arrays.asList(friend));
       
        return userMapper.userToUserDTO(userRepository.saveAndFlush(user));
    }

    /**
     * Save the user in database.
     * @param userDTO the entity to save
     * @param password the password of entity
     * @return UserDTO the persisted entity
     */
    @Override
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
        newUser.setLangKey(userDTO.getLangKey());
        final Optional<Authority> authority = authorityRepository.findById(userDTO.getAuthorityId());
        if (authority.isPresent()) {
            newUser.setAuthority(authority.get());
        }
    
        // new user is active
        newUser.setActivated(true);
        newUser = userRepository.save(newUser);

        LOGGER.debug("Created Information for User: {}", newUser);
        return userMapper.userToUserDTO(newUser);
    }

    /**
     * Returns user by login.
     * @param login of the user
     * @return the entity
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<User> findUserByLogin(String login) {
    	LOGGER.debug("Request find user by login: {}", login);
        return userRepository.findOneByLoginIgnoreCase(login);
    }

    /**
     * Returns user by email.
     * @param email of the user
     * @return the entity
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<User> findUserByEmail(String email) {
    	LOGGER.debug("Request find user by email: {}", email);
        return userRepository.findOneByEmailIgnoreCase(email);
    }
    
    /**
     * Returns User by username.
     * @param username : login or email
     * @return the entity
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<UserDTO> findUserByUsername(String username) {
    	LOGGER.debug("Request find user by username: {}", username);
    	Optional<User> user;
    	user = userRepository.findOneByEmailIgnoreCase(username);
		if (user.isPresent()) {
    		return user.map(userMapper::userToUserDTO);
    	}
		user = userRepository.findOneByLoginIgnoreCase(username);
		if (user.isPresent()) {
    		return user.map(userMapper::userToUserDTO);
    	}
		return Optional.empty();
    }

    /**
	 * Returns all users.
	 * @return the list of entities
	 */
    @Transactional(readOnly = true)
	@Override
	public List<UserDTO> findAll() {
    	LOGGER.debug("Request find all users");
		return userRepository.findAll().stream()
				.map(userMapper::userToUserDTO).collect(Collectors.toList());
	}
    
    /**
     * Update a user.
     * @param userDTO : the entity to update. 
     * @return the entity updated
     */
    @Override
    public UserDTO update(UserDTO userDTO, String password) {
    	User newUser = new User();
    	newUser.setId(userDTO.getId());
    	String encryptedPassword = passwordEncoder.encode(password);
    	newUser.setLogin(userDTO.getLogin());
    	// new user gets initially a generated password
    	newUser.setPassword(encryptedPassword);
    	newUser.setFirstName(userDTO.getFirstName());
    	newUser.setLastName(userDTO.getLastName());
    	newUser.setEmail(userDTO.getEmail());
    	newUser.setImageUrl(userDTO.getImageUrl());
    	newUser.setCountryOfResidence(userDTO.getCountryOfResidence());
    	newUser.setLangKey(userDTO.getLangKey());
    	final Optional<Authority> authority = authorityRepository.findById(userDTO.getAuthorityId());
    	if (authority.isPresent()) {
    		newUser.setAuthority(authority.get());
    	}
   
    	newUser.setActivated(userDTO.getActivated());
    	newUser = userRepository.save(newUser);
    	LOGGER.debug("Updated Information for User: {}", newUser);
    	return userMapper.userToUserDTO(newUser);
    }

}
