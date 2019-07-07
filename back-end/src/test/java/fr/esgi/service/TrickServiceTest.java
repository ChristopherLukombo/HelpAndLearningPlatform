package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.dao.TrickRepository;
import fr.esgi.domain.Category;
import fr.esgi.domain.Subscription;
import fr.esgi.domain.Trick;
import fr.esgi.service.dto.TrickDTO;
import fr.esgi.service.impl.TrickServiceImpl;
import fr.esgi.service.mapper.TrickMapper;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TrickServiceTest {

	private static final String DESCRIPTION = "Blo blob";

	private static final String WORDING = "Computer Science";

	private static final long ID = 1L;

	@Mock
	private TrickRepository trickRepository;

	@Mock
	private SubscriptionRepository subscriptionRepository;

	@Mock
	private TrickMapper trickMapper;

	@InjectMocks
	private TrickServiceImpl trickServiceImpl;

	private static Trick getTrick() {
		Trick trick = new Trick();
		trick.setId(ID);
		trick.setWording(WORDING);
		trick.setCategory( getCategory());
		trick.setCreationDate(LocalDate.now());
		trick.setDescription(DESCRIPTION);
		trick.setViewNumber(0);
		return trick;
	}

	private static TrickDTO getTrickDTO() {
		TrickDTO trickdto = new TrickDTO();
		trickdto.setId(ID);
		trickdto.setWording(WORDING);
		trickdto.setCategoryId(ID);
		trickdto.setCreationDate(LocalDate.now());
		trickdto.setDescription(DESCRIPTION);
		return trickdto;
	}

	private static Subscription getSubscription() {
		Subscription subscription = new Subscription();
		subscription.setId(ID);
		subscription.setSubscriptionDate(LocalDate.now());
		subscription.setTrick(getTrick());
		return subscription;
	}

	private static Category getCategory() {
		Category category = new Category();
		category.setId(ID);
		category.setWording(WORDING);
		return category;
	}

	@Test
	public void shouldfindAllTricksWhenIsEmpty() {
		// Given
		List<Trick> tricks = new ArrayList<>();

		// When
		when(trickRepository.findAll()).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO());

		// Then
		assertThat(trickServiceImpl.findAll()).isEmpty();
	}

	@Test
	public void shouldfindAllTricksWhenIsKO() {
		// Given
		List<Trick> tricks = null;

		// When
		when(trickRepository.findAll()).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(null);

		// Then
		assertThatThrownBy(() -> trickServiceImpl.findAll())
		.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldfindAllNewTricksAvailableByUserIdWhenIsOK() {
		// Given
		List<Trick> tricks = new ArrayList<>();
		tricks.add(getTrick());

		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(getSubscription());

		// When
		when(subscriptionRepository.findAllByUserIdAndStatus(anyLong(), anyBoolean())).thenReturn(subscriptions);
		when(trickRepository.findAllNewTricksAvailableByDateAndCategoryId(any(), anyLong())).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO()).thenReturn(getTrickDTO());

		// Then
		assertThat(trickServiceImpl.findAllNewTricksAvailableByUserId(ID)).isNotEmpty();
	}

	@Test
	public void shouldfindAllNewTricksAvailableByUserIdWhenIsEmpty() {
		// Given
		List<Trick> tricks = new ArrayList<>();

		List<Subscription> subscriptions = new ArrayList<>();

		// When
		when(subscriptionRepository.findAllByUserIdAndStatus(anyLong(), anyBoolean())).thenReturn(subscriptions);
		when(trickRepository.findAllNewTricksAvailableByDateAndCategoryId(any(), anyLong())).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO()).thenReturn(getTrickDTO());

		// Then
		assertThat(trickServiceImpl.findAllNewTricksAvailableByUserId(ID)).isEmpty();
	}

	@Test
	public void shouldfindAllNewTricksAvailableByUserIdWhenIsNull() {
		// Given
		List<Trick> tricks = new ArrayList<>();

		List<Subscription> subscriptions = null;

		// When
		when(subscriptionRepository.findAllByUserIdAndStatus(anyLong(), anyBoolean())).thenReturn(subscriptions);
		when(trickRepository.findAllNewTricksAvailableByDateAndCategoryId(any(), anyLong())).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO()).thenReturn(getTrickDTO());

		// Then
		assertThat(trickServiceImpl.findAllNewTricksAvailableByUserId(ID)).isEmpty();
	}


	@Test
	public void shouldSaveTrickWhenIsOK() {
		// Given
		Trick trick = getTrick();

		TrickDTO trickdto = getTrickDTO();

		// When
		when(trickRepository.save(mock(Trick.class))).thenReturn(trick);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO()).thenReturn(trickdto);

		// Then
		assertThat(trickServiceImpl.save(trickdto)).isNotNull();
	}

	@Test
	public void shouldSaveTrickWhenIsKO() {
		// Given
		TrickDTO trickdto = null;

		// When
		when(trickRepository.save(mock(Trick.class))).thenReturn(null);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(null);

		// Then
		assertThat(trickServiceImpl.save(trickdto)).isNull();
	}

	@Test
	public void shouldUpdateTrickWhenIsOK() {
		// Given
		Trick trick = getTrick();

		TrickDTO trickdto = getTrickDTO();

		// When
		when(trickRepository.saveAndFlush(mock(Trick.class))).thenReturn(trick);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO()).thenReturn(trickdto);

		// Then
		assertThat(trickServiceImpl.update(trickdto)).isNotNull();
	}

	@Test
	public void shouldUpdateTrickWhenIsKO() {
		// Given
		TrickDTO trickDTO = null;

		// When
		when(trickRepository.saveAndFlush(mock(Trick.class))).thenReturn(null);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(null);

		// Then
		assertThat(trickServiceImpl.update(trickDTO)).isNull();
	}

	@Test
	public void shouldFindTheMostLatestsWhenIsEmpty() {
		// Given
		List<Trick> tricks = new ArrayList<>();

		// When
		when(trickRepository.findTheMostLatests()).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO()).thenReturn(null);

		// Then
		assertThat(trickServiceImpl.findTheMostLatests()).isEmpty();
	}

	@Test
	public void shouldFindTheMostLatestsWhenIsOK() {
		// Given
		List<Trick> tricks = new ArrayList<>();
		tricks.add(new Trick());

		// When
		when(trickRepository.findTheMostLatests()).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO()).thenReturn(getTrickDTO());

		// Then
		assertThat(trickServiceImpl.findTheMostLatests()).isNotEmpty();
	}

	@Test
	public void shouldFindTheMostLatestsWhenIsKO() {
		// Given
		List<Trick> tricks = null;

		// When
		when(trickRepository.findTheMostLatests()).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO()).thenReturn(null);

		// Then
		assertThatThrownBy(() -> trickServiceImpl.findTheMostLatests())
		.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldFindTheMostViewedWhenIsOK() {
		// Given
		List<Trick> tricks = new ArrayList<>();
		tricks.add(new Trick());

		// When
		when(trickRepository.findTheMostViewed()).thenReturn(tricks);

		// Then
		assertThat(trickServiceImpl.findTheMostViewed()).isNotEmpty();
	}

	@Test
	public void shouldFindTheMostViewedWhenIsEmpty() {
		// Given
		List<Trick> tricks = new ArrayList<>();

		// When
		when(trickRepository.findTheMostViewed()).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO()).thenReturn(null);

		// Then
		assertThat(trickServiceImpl.findTheMostViewed()).isEmpty();
	}

	@Test
	public void shouldFindTheMostViewedWhenIsKO() {
		// Given
		List<Trick> tricks = null;

		// When
		when(trickRepository.findTheMostViewed()).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO(((Trick) any()))).thenReturn(getTrickDTO()).thenReturn(null);

		// Then
		assertThatThrownBy(() -> trickServiceImpl.findTheMostViewed())
		.isInstanceOf(NullPointerException.class);
	}

	@Test
	public void shouldDeleteWhenIsOK() {
		// Given
		doNothing().when(trickRepository).deleteById(anyLong());

		// When
		trickServiceImpl.delete(anyLong());

		// Then
		verify(trickRepository, times(1)).deleteById(anyLong());
	}

	@Test
	public void shouldAddViewToTrickWhenIsOK() {
		// Given
		Trick trick = getTrick();

		// When
		when(trickRepository.findById(anyLong())).thenReturn(Optional.ofNullable(trick));
		when(trickRepository.saveAndFlush((Trick) any())).thenReturn(trick);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(getTrickDTO());

		// Then
		assertThat(trickServiceImpl.addViewToTrick(ID)).isNotNull();
	}

	@Test
	public void shouldAddViewToTrickWhenIsKO() {
		// Given
		Trick trick = null;

		// When
		when(trickRepository.findById(anyLong())).thenReturn(Optional.ofNullable(trick));
		when(trickRepository.saveAndFlush((Trick) any())).thenReturn(trick);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(null);

		// Then
		assertThat(trickServiceImpl.addViewToTrick(ID)).isNull();
	}

	@Test
	public void shouldFindAllByWordingWhenIsOK() {
		// Given
		List<Trick> content = new ArrayList<>();
		content.add(getTrick());
		Page<Trick> tricks = new PageImpl<Trick>(content);

		// When
		when(trickRepository.findAllByWording((Pageable) any(), anyString())).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(getTrickDTO());

		// Then		
		assertThat(trickServiceImpl.findAllByWording((Pageable) any(), anyString())).isNotEmpty();
	}

	@Test
	public void shouldFindAllByWordingWhenIsEmpty() {
		// Given
		List<Trick> content = new ArrayList<>();
		Page<Trick> tricks = new PageImpl<Trick>(content);

		// When
		when(trickRepository.findAllByWording((Pageable) any(), anyString())).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(null);

		// Then		
		assertThat(trickServiceImpl.findAllByWording((Pageable) any(), anyString())).isEmpty();
	}

	@Test
	public void shouldFindAllByWordingWhenIsKO() {
		// Given
		Page<Trick> tricks = null;

		// When
		when(trickRepository.findAllByWording((Pageable) any(), anyString())).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(null);

		// Then		
		assertThatThrownBy(() -> trickServiceImpl.findAllByWording((Pageable) any(), anyString()))
		.isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void shouldFindAllByOwnUserIdWhenIsOK() {
		// Given
		List<Trick> content = new ArrayList<>();
		content.add(getTrick());
		Page<Trick> tricks = new PageImpl<Trick>(content);

		// When
		when(trickRepository.findAllByOwnUserId((Pageable) any(), anyLong())).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(getTrickDTO());

		// Then		
		assertThat(trickServiceImpl.findAllByOwnUserId((Pageable) any(), anyLong())).isNotEmpty();
	}
	
	@Test
	public void shouldFindAllByOwnUserIdWhenIsEmpty() {
		// Given
		List<Trick> content = new ArrayList<>();
		Page<Trick> tricks = new PageImpl<Trick>(content);

		// When
		when(trickRepository.findAllByOwnUserId((Pageable) any(), anyLong())).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(null);

		// Then		
		assertThat(trickServiceImpl.findAllByOwnUserId(PageRequest.of(0, 10), ID)).isEmpty();
	}
	
	@Test
	public void shouldFindAllByOwnUserIdWhenIsKO() {
		// Given
		Page<Trick> tricks = null;

		// When
		when(trickRepository.findAllByOwnUserId((Pageable) any(), anyLong())).thenReturn(tricks);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(null);

		// Then		
		assertThatThrownBy(() -> trickServiceImpl.findAllByOwnUserId(PageRequest.of(0, 10), ID))
		.isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void shouldFindOneWhenIsOK() {
		// Given
		Trick trick = getTrick();
		
		// When
		when(trickRepository.findById(anyLong())).thenReturn(Optional.ofNullable(trick));
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(getTrickDTO());
		
		// Then
		assertThat(trickServiceImpl.findOne(ID)).isNotEqualTo(Optional.empty());
	}
	
	@Test
	public void shouldFindOneWhenIsEmpty() {
		// Given
		Trick trick = null;
		
		// When
		when(trickRepository.findById(anyLong())).thenReturn(Optional.ofNullable(trick));
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(null);
		
		// Then
		assertThat(trickServiceImpl.findOne(ID)).isEqualTo(Optional.empty());
	}
	
	@Test
	public void shouldFindAllByUserIdAndStatusWhenIsOK() {
		// Given
		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(getSubscription());
		
		// When
		when(subscriptionRepository.findAllByUserIdAndStatus(anyLong(), anyBoolean())).thenReturn(subscriptions);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(getTrickDTO());
		
		// Then
		assertThat(trickServiceImpl.findAllByUserIdAndStatus(ID, true)).isNotEmpty();
	}
	
	@Test
	public void shouldFindAllByUserIdAndStatusWhenIsEmpty() {
		// Given
		List<Subscription> subscriptions = new ArrayList<>();
		
		// When
		when(subscriptionRepository.findAllByUserIdAndStatus(anyLong(), anyBoolean())).thenReturn(subscriptions);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(null);
		
		// Then
		assertThat(trickServiceImpl.findAllByUserIdAndStatus(ID, true)).isEmpty();
	}
	
	@Test
	public void shouldFindAllByUserIdAndStatusWhenIsKO() {
		// Given
		List<Subscription> subscriptions = null;
		
		// When
		when(subscriptionRepository.findAllByUserIdAndStatus(anyLong(), anyBoolean())).thenReturn(subscriptions);
		when(trickMapper.trickToTrickDTO((Trick) any())).thenReturn(null);
		
		// Then
		assertThat(trickServiceImpl.findAllByUserIdAndStatus(ID, true)).isEmpty();
	}

}
