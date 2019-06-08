package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.dao.TrickRepository;
import fr.esgi.domain.Category;
import fr.esgi.domain.Subscription;
import fr.esgi.domain.Trick;
import fr.esgi.domain.User;
import fr.esgi.service.dto.TrickDTO;
import fr.esgi.service.impl.TrickServiceImpl;
import fr.esgi.service.mapper.TrickMapper;

@Profile("test")
@RunWith(MockitoJUnitRunner.Silent.class)
public class TrickServiceTest {

	private static final String DESCRIPTION = "Blo blob";

	private static final long OWN_USER_ID = 1L;

	private static final long CATEGORY_ID = 1L;

	private static final String WORDING = "Computer Science";

	private static final long OWNER_USER_ID = 1L;

	private static final long ID = 1L;

	@Mock
	private TrickRepository trickRepository;

	@Mock
	private SubscriptionRepository subscriptionRepository;

	@Mock
	private TrickMapper trickMapper;

	@InjectMocks
	private TrickServiceImpl trickServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		trickServiceImpl = new TrickServiceImpl(
				trickRepository, subscriptionRepository, trickMapper);
	}
	
	@Test
	public void shouldfindAllTricksWhenIsEmpty() {
		// Given
		List<TrickDTO> tricksDTOs = new ArrayList<>();

		// When
		when(trickServiceImpl.findAll()).thenReturn(tricksDTOs);

		// Then
		assertThat(trickServiceImpl.findAll()).isEqualTo(tricksDTOs);
	}
	
	@Test
	public void shouldfindAllTricksWhenIsKO() {
		// Given
		List<TrickDTO> tricksDTOs = null;

		// When
		when(trickServiceImpl.findAll()).thenReturn(tricksDTOs);
		
		// Then
		assertThatThrownBy(() -> trickServiceImpl.findAll())
        .isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldfindAllNewTricksAvailableByUserIdWhenIsEmpty() {
		// Given
		List<TrickDTO> tricksDTO = new ArrayList<>();

		// When
		when(trickServiceImpl.findAllNewTricksAvailableByUserId(anyLong())).thenReturn(tricksDTO);

		// Then
		assertThat(trickServiceImpl.findAllNewTricksAvailableByUserId(anyLong())).isEmpty();
	}


	@Test
	public void shouldfindAllNewTricksAvailableByUserIdWhenIsNull() {
		// Given
		List<TrickDTO> tricksDTO = new ArrayList<>();

		// When
		when(subscriptionRepository.findAllByUserId(anyLong())).thenReturn(null);
		when(trickServiceImpl.findAllNewTricksAvailableByUserId(anyLong())).thenReturn(tricksDTO);

		// Then
		assertThat(trickServiceImpl.findAllNewTricksAvailableByUserId(anyLong())).isEmpty();
	}

	@Test
	public void shouldfindAllNewTricksAvailableByUserIdWhenIsOK() {
		// Given
		TrickDTO trickDTO = new TrickDTO();
		trickDTO.setId(ID);
		trickDTO.setOwnUserId(OWNER_USER_ID);
		trickDTO.setWording(WORDING);
		trickDTO.setCategoryId(CATEGORY_ID);
		trickDTO.setOwnUserId(OWN_USER_ID);
		trickDTO.setCreationDate(LocalDate.now());
		trickDTO.setDescription(DESCRIPTION);
		
		Category category = new Category();
		category.setId(ID);
		category.setWording(WORDING);

		Trick trick = new Trick();
		trick.setId(ID);
		trick.setOwnUser(new User());
		trick.setWording(WORDING);

		trick.setCategory(category);
		trick.setCreationDate(LocalDate.now());
		trick.setDescription(DESCRIPTION);	

		List<Trick> tricks = new ArrayList<>();
		tricks.add(trick);

		List<TrickDTO> tricksDTO = new ArrayList<>();
		tricksDTO.add(trickDTO);

		Subscription subscription = new Subscription();
		subscription.setId(ID);
		subscription.setTrick(trick);
		subscription.setUser(new User());
		subscription.setSubscriptionDate(LocalDate.now());

		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(subscription);

		// When
		when(subscriptionRepository.findAllByUserId(anyLong())).thenReturn(subscriptions);
		List<TrickDTO> trc = trickServiceImpl.findAllNewTricksAvailableByUserId(anyLong());
		when(trc).thenReturn(tricksDTO);

		// Then
		assertThat(trc).isNotNull();
	}
	
	@Test
	public void shouldUpdateTrickWhenIsOK() {
		// Given
		TrickDTO trickDTO = new TrickDTO();
		trickDTO.setId(ID);
		trickDTO.setWording(WORDING);
		trickDTO.setCategoryId(ID);
		trickDTO.setCreationDate(LocalDate.now());
		trickDTO.setDescription(DESCRIPTION);
		
		// When
		when(trickServiceImpl.update(mock(TrickDTO.class))).thenReturn(trickDTO);
		
		// Then
		assertThat(trickServiceImpl.update(mock(TrickDTO.class))).isEqualTo(trickDTO);
	}
	
	@Test
	public void shouldUpdateTrickWhenIsKO() {
		// Given
		TrickDTO trickDTO = null;
		
		// When
		when(trickServiceImpl.update(mock(TrickDTO.class))).thenReturn(trickDTO);
		
		// Then
		assertThat(trickServiceImpl.update(mock(TrickDTO.class))).isEqualTo(trickDTO);
	}
}
