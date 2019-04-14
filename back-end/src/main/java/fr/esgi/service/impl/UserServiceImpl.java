package fr.esgi.service.impl;

import fr.esgi.dao.UserRepository;
import fr.esgi.domain.User;
import fr.esgi.service.UserService;
import fr.esgi.service.dto.UserDTO;
import fr.esgi.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public void addFriend(UserDTO userDTO, UserDTO friendToAdd) {
        User user = userMapper.userDTOToUser(userDTO);

        User friend = userMapper.userDTOToUser(friendToAdd);

        user.addFriends(Arrays.asList(friend));

        userRepository.saveAndFlush(user);
    }
}
