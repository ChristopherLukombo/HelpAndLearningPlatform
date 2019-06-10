package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.esgi.dao.AuthorityRepository;
import fr.esgi.dao.UserRepository;
import fr.esgi.domain.Authority;
import fr.esgi.domain.User;
import fr.esgi.service.dto.UserDTO;
import fr.esgi.service.impl.UserServiceImpl;
import fr.esgi.service.mapper.UserMapper;

@Profile("test")
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

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
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userServiceImpl = new UserServiceImpl(userRepository, userMapper, authorityRepository, passwordEncoder);
	}

	@Test
	public void shouldAddFriendWhenIsOK() {
		// Given
		UserDTO userDTO = mock(UserDTO.class);
		userDTO.setLogin(LOGIN);
		userDTO.setFirstName(FIRST_NAME);
		userDTO.setLastName(LAST_NAME);
		userDTO.setEmail(EMAIL);
		userDTO.setImageUrl(IMAGE_URL);
		userDTO.setCountryOfResidence(COUNTRY_OF_RESIDENCE);
		userDTO.setAuthorityId(AUTHORITY_ID);
		
		User user = new User();
		user.setLogin(LOGIN);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setEmail(EMAIL);
		user.setImageUrl(IMAGE_URL);
		user.setCountryOfResidence(COUNTRY_OF_RESIDENCE);
		
		// When
		when(userMapper.userDTOToUser(userDTO)).thenReturn(user);
		userServiceImpl.addFriend(userDTO, userDTO);
		
		// Then
		verify(userRepository, times(1)).saveAndFlush(user);
		assertThat(user.getFriends()).isNotEmpty();
	}

	@Test
	public void shouldRegisterUserWhenIsOK() {
		// Given
		UserDTO userDTO = new UserDTO();
		userDTO.setLogin(LOGIN);
		userDTO.setFirstName(FIRST_NAME);
		userDTO.setLastName(LAST_NAME);
		userDTO.setEmail(EMAIL);
		userDTO.setImageUrl(IMAGE_URL);
		userDTO.setCountryOfResidence(COUNTRY_OF_RESIDENCE);
		userDTO.setAuthorityId(AUTHORITY_ID);
		
		Authority authority = new Authority();
		authority.setId(AUTHORITY_ID);
		authority.setName("ROLE_CUSTOMER");
		
		// When
		when(authorityRepository.findById(anyLong())).thenReturn(Optional.of(authority));
		when(userServiceImpl.registerUser(mock(UserDTO.class), anyString())).thenReturn(userDTO);
		
		// Then
		assertThat(userServiceImpl.registerUser(mock(UserDTO.class), anyString())).isEqualTo(userDTO);
	}


	@Test
	public void shouldRegisterUserWhenIsKO() {
		// Given
		UserDTO userDTO = null;
		
		// When
		when(userServiceImpl.registerUser(mock(UserDTO.class), anyString())).thenReturn(userDTO);
		
		// Then
		assertThat(userServiceImpl.registerUser(mock(UserDTO.class), anyString())).isEqualTo(userDTO);
	}

	@Test
	public void shouldfindUserByLoginWhenIsOK() {
		// Given
		User user = new User();
		user.setLogin(LOGIN);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setEmail(EMAIL);
		user.setImageUrl(IMAGE_URL);
		user.setCountryOfResidence(COUNTRY_OF_RESIDENCE);
		
		// When
		when(userServiceImpl.findUserByLogin(anyString())).thenReturn(Optional.ofNullable(user));
		
		// Then
		assertThat(userServiceImpl.findUserByLogin(anyString())).isEqualTo(Optional.ofNullable(user));
	}

	@Test
	public void shouldfindUserByLoginWhenIsKO() {
		// Given
		User user = null;
		
		// When
		when(userServiceImpl.findUserByLogin(anyString())).thenReturn(Optional.ofNullable(user));
		
		// Then
		assertThat(userServiceImpl.findUserByLogin(anyString())).isEqualTo(Optional.ofNullable(user));
	}

	@Test
	public void shouldfindUserByEmailWhenIsOK() {
		// Given
		User user = new User();
		user.setLogin(LOGIN);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setEmail(EMAIL);
		user.setImageUrl(IMAGE_URL);
		user.setCountryOfResidence(COUNTRY_OF_RESIDENCE);
		
		// When
		when(userServiceImpl.findUserByEmail(anyString())).thenReturn(Optional.ofNullable(user));
		
		// Then
		assertThat(userServiceImpl.findUserByEmail(anyString())).isEqualTo(Optional.ofNullable(user));
	}


	@Test
	public void shouldfindUserByEmailWhenIsKO() {
		// Given
		User user = null;
		
		// When
		when(userServiceImpl.findUserByEmail(anyString())).thenReturn(Optional.ofNullable(user));
		
		// Then
		assertThat(userServiceImpl.findUserByEmail(anyString())).isEqualTo(Optional.ofNullable(user));
	}
	
	@Test
	public void shouldFindAllWhenIsOK() {
		// Given
		List<UserDTO> usersDTO = new ArrayList<>();
		usersDTO.add(new UserDTO());
		
		List<User> users = new ArrayList<>();
		users.add(new User());
		
		// When
		when(userRepository.findAll()).thenReturn(users);
		
		// Then
		assertThat(userServiceImpl.findAll()).isNotEmpty();
	}
	
	@Test
	public void shouldFindAllWhenIsEmpty() {
		// Given
		List<User> users = new ArrayList<>();
		
		// When
		when(userRepository.findAll()).thenReturn(users);
		
		// Then
		assertThat(userServiceImpl.findAll()).isEmpty();
	}
	
	@Test
	public void shouldFindAllWhenIsKO() {
		// Given
		List<User> users = null;
		
		// When
		when(userRepository.findAll()).thenReturn(users);
		
		// Then
		assertThatThrownBy(() -> userServiceImpl.findAll())
		.isInstanceOf(NullPointerException.class);
	}

}
