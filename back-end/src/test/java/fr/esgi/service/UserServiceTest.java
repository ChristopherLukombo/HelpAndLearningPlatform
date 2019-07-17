package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.esgi.dao.AuthorityRepository;
import fr.esgi.dao.UserRepository;
import fr.esgi.domain.Authority;
import fr.esgi.domain.User;
import fr.esgi.service.dto.UserDTO;
import fr.esgi.service.impl.UserServiceImpl;
import fr.esgi.service.mapper.UserMapper;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

	private static final long ID = 1L;

	private static final long AUTHORITY_ID = 1L;

	private static final String COUNTRY_OF_RESIDENCE = "France";

	private static final String IMAGE_URL = "test";

	private static final String EMAIL = "ben.lokwood@gmail.com";

	private static final String LAST_NAME = "lokwood";

	private static final String FIRST_NAME = "Ben";

	private static final String LOGIN = "Ben.lokwood";

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserMapper userMapper;

	@Mock 
	private AuthorityRepository authorityRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private static User getUser() {
		User user = new User();
		user.setId(ID);
		user.setLogin(LOGIN);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setEmail(EMAIL);
		user.setImageUrl(IMAGE_URL);
		user.setCountryOfResidence(COUNTRY_OF_RESIDENCE);
		return user;
	}

	private static UserDTO getUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(ID);
		userDTO.setLogin(LOGIN);
		userDTO.setFirstName(FIRST_NAME);
		userDTO.setLastName(LAST_NAME);
		userDTO.setEmail(EMAIL);
		userDTO.setImageUrl(IMAGE_URL);
		userDTO.setCountryOfResidence(COUNTRY_OF_RESIDENCE);
		userDTO.setAuthorityId(AUTHORITY_ID);
		return userDTO;
	}

	private static Authority getAuthority() {
		Authority authority = new Authority();
		authority.setId(AUTHORITY_ID);
		authority.setName("ROLE_CUSTOMER");
		return authority;
	}

	@Test
	public void shouldAddFriendWhenIsOK() {
		// Given
		UserDTO userDTO = getUserDTO();

		User user = getUser();

		// When
		when(userMapper.userDTOToUser((UserDTO) any())).thenReturn(user);
		when(userRepository.saveAndFlush((User) any())).thenReturn(user);
		when(userMapper.userToUserDTO((User) any())).thenReturn(userDTO);

		// Then
		assertThat(userServiceImpl.addFriend(userDTO, userDTO)).isNotNull();
	}

	@Test
	public void shouldAddFriendWhenIsKO() {
		// Given
		UserDTO userDTO = null;

		User user = null;

		// When
		when(userMapper.userDTOToUser((UserDTO) any())).thenReturn(user);
		when(userRepository.saveAndFlush((User) any())).thenReturn(user);
		when(userMapper.userToUserDTO((User) any())).thenReturn(userDTO);

		// Then
		assertThatThrownBy(() -> userServiceImpl.addFriend(userDTO, userDTO))
		.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldRegisterUserWhenIsOK1() {
		// Given
		UserDTO userDTO = getUserDTO();

		User user = getUser();

		Authority authority = getAuthority();

		// When
		when(passwordEncoder.encode(anyString())).thenReturn("TEST");
		when(authorityRepository.findById(anyLong())).thenReturn(Optional.of(authority));
		when(userRepository.save((User) any())).thenReturn(user);
		when(userMapper.userToUserDTO((User) any())).thenReturn(userDTO);

		// Then
		assertThat(userServiceImpl.registerUser(userDTO, "TEST")).isNotNull();
	}
	
	@Test
	public void shouldRegisterUserWhenIsOK2() {
		// Given
		UserDTO userDTO = getUserDTO();

		User user = getUser();

		// When
		when(passwordEncoder.encode(anyString())).thenReturn("TEST");
		when(authorityRepository.findById(anyLong())).thenReturn(Optional.empty());
		when(userRepository.save((User) any())).thenReturn(user);
		when(userMapper.userToUserDTO((User) any())).thenReturn(userDTO);

		// Then
		assertThat(userServiceImpl.registerUser(userDTO, "TEST")).isNotNull();
	}

	@Test
	public void shouldRegisterUserWhenIsKO() {
		// Given
		UserDTO userDTO = null;

		// When
		when(passwordEncoder.encode(anyString())).thenReturn(null);
		when(authorityRepository.findById(anyLong())).thenReturn(Optional.empty());
		when(userRepository.save((User) any())).thenReturn(null);
		when(userMapper.userToUserDTO((User) any())).thenReturn(null);

		// Then
		assertThatThrownBy(() -> userServiceImpl.registerUser(userDTO, "TEST"))
		.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldfindUserByLoginWhenIsOK() {
		// Given
		User user = getUser();

		// When
		when(userRepository.findOneByLoginIgnoreCase(anyString())).thenReturn(Optional.ofNullable(user));

		// Then
		assertThat(userServiceImpl.findUserByLogin(LOGIN)).isEqualTo(Optional.ofNullable(user));
	}

	@Test
	public void shouldfindUserByLoginWhenIsKO() {
		// Given
		User user = null;

		// When
		when(userRepository.findOneByLoginIgnoreCase(anyString())).thenReturn(Optional.ofNullable(user));

		// Then
		assertThat(userServiceImpl.findUserByLogin(LOGIN)).isEqualTo(Optional.empty());
	}

	@Test
	public void shouldFindUserByIdWhenIsOK() {
		// Given
		User user = getUser();

		UserDTO userDTO = getUserDTO();

		// When
		when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
		when(userMapper.userToUserDTO((User) any())).thenReturn(userDTO);

		// Then
		assertThat(userServiceImpl.findUserById(ID)).isEqualTo(Optional.ofNullable(userDTO));
	}



	@Test
	public void shouldFindUserByIdWhenIsKO() {
		// Given
		User user = null;

		UserDTO userDTO = null;

		// When
		when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
		when(userMapper.userToUserDTO((User) any())).thenReturn(userDTO);

		// Then
		assertThat(userServiceImpl.findUserById(1L)).isEqualTo(Optional.empty());
	}

	@Test
	public void shouldfindUserByEmailWhenIsOK() {
		// Given
		User user = getUser();

		// When
		when(userRepository.findOneByEmailIgnoreCase(anyString())).thenReturn(Optional.ofNullable(user));

		// Then
		assertThat(userServiceImpl.findUserByEmail(EMAIL)).isEqualTo(Optional.ofNullable(user));
	}


	@Test
	public void shouldfindUserByEmailWhenIsKO() {
		// Given
		User user = null;

		// When
		when(userRepository.findOneByEmailIgnoreCase(anyString())).thenReturn(Optional.ofNullable(user));

		// Then
		assertThat(userServiceImpl.findUserByEmail(anyString())).isEqualTo(Optional.empty());
	}

	@Test
	public void shouldFindAllWhenIsOK() {
		// Given
		List<User> users = new ArrayList<>();
		users.add(new User());

		// When
		when(userRepository.findAll()).thenReturn(users);
		when(userMapper.userToUserDTO((User) any())).thenReturn(new UserDTO());

		// Then
		assertThat(userServiceImpl.findAll()).isNotEmpty();
	}

	@Test
	public void shouldFindAllWhenIsEmpty() {
		// Given
		List<User> users = new ArrayList<>();

		// When
		when(userRepository.findAll()).thenReturn(users);
		when(userMapper.userToUserDTO((User) any())).thenReturn(null);

		// Then
		assertThat(userServiceImpl.findAll()).isEmpty();
	}

	@Test
	public void shouldFindAllWhenIsKO() {
		// Given
		List<User> users = null;

		// When
		when(userRepository.findAll()).thenReturn(users);
		when(userMapper.userToUserDTO((User) any())).thenReturn(null);

		// Then
		assertThatThrownBy(() -> userServiceImpl.findAll())
		.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldFindUserByUsernameWithEmailWhenIsOk() {
		// Given
		User user = getUser();

		// When
		when(userRepository.findOneByEmailIgnoreCase(anyString())).thenReturn(Optional.ofNullable(user));
		when(userMapper.userToUserDTO((User) any())).thenReturn(getUserDTO());
		when(userRepository.findOneByLoginIgnoreCase(anyString())).thenReturn(Optional.ofNullable(user));

		// Then
		assertThat(userServiceImpl.findUserByUsername(anyString())).isNotEqualTo(Optional.empty());
	}

	@Test
	public void shouldFindUserByUsernameWithLoginWhenIsOk() {
		// Given
		User user = getUser();

		// When
		when(userRepository.findOneByEmailIgnoreCase(anyString())).thenReturn(Optional.ofNullable(null));
		when(userMapper.userToUserDTO((User) any())).thenReturn(null);
		when(userRepository.findOneByLoginIgnoreCase(anyString())).thenReturn(Optional.ofNullable(user));
		when(userMapper.userToUserDTO((User) any())).thenReturn(getUserDTO());

		// Then
		assertThat(userServiceImpl.findUserByUsername(anyString())).isNotEqualTo(Optional.empty());
	}

	@Test
	public void shouldFindUserByUsernameWhenIsKO() {
		// Given
		User user = null;

		// When
		when(userRepository.findOneByEmailIgnoreCase(anyString())).thenReturn(Optional.ofNullable(null));
		when(userMapper.userToUserDTO((User) any())).thenReturn(null);
		when(userRepository.findOneByLoginIgnoreCase(anyString())).thenReturn(Optional.ofNullable(user));
		when(userMapper.userToUserDTO((User) any())).thenReturn(null);

		// Then
		assertThat(userServiceImpl.findUserByUsername(anyString())).isEqualTo(Optional.empty());
	}

	@Test
	public void shouldUpdateWhenIsOK() {
		// Given
		User user = getUser();

		// When
		when(authorityRepository.findById(anyLong())).thenReturn(Optional.ofNullable(getAuthority()));
		when(userRepository.saveAndFlush((User) any())).thenReturn(user);
		when(userMapper.userToUserDTO((User) any())).thenReturn(getUserDTO());

		// Then
		assertThat(userServiceImpl.update(getUserDTO(), "TEST")).isNotNull();
	}
	
	@Test
	public void shouldUpdateWhenIsOK1() {
		// Given
		User user = getUser();
		user.setPassword("TEST");

		// When
		when(authorityRepository.findById(anyLong())).thenReturn(Optional.empty());
		when(userRepository.saveAndFlush((User) any())).thenReturn(user);
		when(userMapper.userToUserDTO((User) any())).thenReturn(getUserDTO());
		when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

		// Then
		assertThat(userServiceImpl.update(getUserDTO(), null)).isNotNull();
	}

	@Test
	public void shouldUpdateWhenIsKO1() {
		// Given
		User user = null;

		// When
		when(authorityRepository.findById(anyLong())).thenReturn(Optional.empty());
		when(userRepository.saveAndFlush((User) any())).thenReturn(user);
		when(userMapper.userToUserDTO((User) any())).thenReturn(null);

		// Then
		assertThat(userServiceImpl.update(getUserDTO(), "TEST")).isNull();
	}
	
	@Test
	public void shouldUpdateWhenIsKO2() {
		// Given
		User user = null;

		// When
		when(authorityRepository.findById(anyLong())).thenReturn(Optional.empty());
		when(userRepository.saveAndFlush((User) any())).thenReturn(user);
		when(userMapper.userToUserDTO((User) any())).thenReturn(null);

		// Then
		assertThat(userServiceImpl.update(getUserDTO(), null)).isNull();
	}

}
