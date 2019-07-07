package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
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

import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.domain.Subscription;
import fr.esgi.domain.User;
import fr.esgi.service.dto.SubscriptionDTO;
import fr.esgi.service.impl.SubscriptionServiceImpl;
import fr.esgi.service.mapper.SubscriptionMapper;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SubscriptionServiceTest {

	private static final long ID = 1L;

	private static final String COUNTRY_OF_RESIDENCE = "France";

	private static final String IMAGE_URL = "test";

	private static final String EMAIL = "ben.lokwood@gmail.com";

	private static final String LAST_NAME = "lokwood";

	private static final String FIRST_NAME = "Ben";

	private static final String LOGIN = "Ben.lokwood";

	@Mock
	private SubscriptionRepository subscriptionRepository;

	@Mock
	private SubscriptionMapper subscriptionMapper;
	
	@InjectMocks
	private SubscriptionServiceImpl subscriptionServiceImpl;
	
	private static Subscription getSubscription() {
		Subscription subscription = new Subscription();
		subscription.setId(ID);
		subscription.setFinished(true);
		subscription.setSubscriptionDate(LocalDate.now());
        subscription.setUser(getUser());
		return subscription;
	}
	
	private static SubscriptionDTO getSubscriptionDTO() {
		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
		subscriptionDTO.setId(ID);
		subscriptionDTO.setFinished(true);
		subscriptionDTO.setSubscriptionDate(LocalDate.now());
		subscriptionDTO.setUserId(ID);
		return subscriptionDTO;
	}
	
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

	@Test
	public void shouldFindAllWhenIsOK() {
		// Given
		Subscription subcription = getSubscription();
		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(subcription);

		// When
		when(subscriptionRepository.findAll()).thenReturn(subscriptions);
		when(subscriptionMapper.subscriptionToSubscriptionDTO((Subscription) any())).thenReturn(getSubscriptionDTO());
		
		// Then
		assertThat(subscriptionServiceImpl.findAll()).isNotEmpty();
	}
	
	@Test
	public void shouldFindAllWhenIsEmpty() {
		// Given
		List<Subscription> subscriptions = new ArrayList<>();

		// When
		when(subscriptionRepository.findAll()).thenReturn(subscriptions);
//		when(subscriptionMapper.subscriptionToSubscriptionDTO((Subscription) any())).thenReturn(null);

		// Then
		assertThat(subscriptionServiceImpl.findAll()).isEmpty();
	}
	
	@Test
	public void shouldFindAllWhenIsEmptyIsKO() {
		// Given
		List<Subscription> subscriptionDTOs = null;

		// When
		when(subscriptionRepository.findAll()).thenReturn(subscriptionDTOs);

		// Then
		assertThatThrownBy(() -> subscriptionServiceImpl.findAll())
		.isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void shouldDeleteWhenIsOK() {
		// Given
		doNothing().when(subscriptionRepository).deleteById(anyLong());
		
		// When
	    subscriptionServiceImpl.delete(anyLong());
	    
	    // Then
	    verify(subscriptionRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	public void shouldFindOneWhenIsOk() {
		// Given
		Subscription subcription = getSubscription();
		
		// When
		when(subscriptionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(subcription));
		when(subscriptionMapper.subscriptionToSubscriptionDTO((Subscription) any())).thenReturn(getSubscriptionDTO());
		
		// Then
		assertThat(subscriptionServiceImpl.findOne(ID)).isNotEqualTo(Optional.empty());
	}
	
	@Test
	public void shouldFindOneWhenIsKO() {
		// Given
		Subscription subcription = null;
		
		// When
		when(subscriptionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(subcription));
		
		// Then
		assertThat(subscriptionServiceImpl.findOne(ID)).isEqualTo(Optional.empty());
	}
	
	@Test
	public void shouldSetToFinishedWhenIsOK() {
		// Given
		Subscription subcription = getSubscription();
		
		// When
		when(subscriptionRepository.findBySubscriptionIdAndUserId(anyLong())).thenReturn(subcription);
		when(subscriptionRepository.saveAndFlush((Subscription) any())).thenReturn(subcription);
		when(subscriptionMapper.subscriptionToSubscriptionDTO((Subscription) any())).thenReturn(getSubscriptionDTO());
		
		// Then
		assertThat(subscriptionServiceImpl.setToFinished(ID)).isNotNull(); 
	}
	
	@Test
	public void shouldSetToFinishedWhenIsKO() {
		// Given
		Subscription subcription = null;
		
		// When
		when(subscriptionRepository.findBySubscriptionIdAndUserId(anyLong())).thenReturn(subcription);
		
		// Then
		assertThatThrownBy(() -> subscriptionServiceImpl.setToFinished(ID))
		.isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void shouldIsSubscribedWhenIsOK() {
		// Given
		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(getSubscription());
		
		// When
		when(subscriptionRepository.findAllByTrickId(anyLong(), anyLong())).thenReturn(subscriptions);
		when(subscriptionMapper.subscriptionToSubscriptionDTO((Subscription) any())).thenReturn(getSubscriptionDTO());
		
		// Then
		assertThat(subscriptionServiceImpl.isSubscribed(getSubscriptionDTO())).isFalse();
	}
	
	@Test
	public void shouldSaveWhenIsOK() {
		// Given
		Subscription subcription = getSubscription();
		
		// When
		when(subscriptionMapper.subscriptionDTOToSubscription((SubscriptionDTO)any())).thenReturn(subcription);
		when(subscriptionRepository.save((Subscription) any())).thenReturn(subcription);
		when(subscriptionMapper.subscriptionToSubscriptionDTO((Subscription) any())).thenReturn(getSubscriptionDTO());
		
		// Then
		assertThat(subscriptionServiceImpl.save(getSubscriptionDTO())).isNotNull();
	}
	
	@Test
	public void shouldSaveWIthNoDateWhenIsOK() {
		// Given
		Subscription subcription = getSubscription();
		subcription.setSubscriptionDate(null);
		SubscriptionDTO subscriptionDTO = getSubscriptionDTO();
		subscriptionDTO.setSubscriptionDate(null);
		
		// When
		when(subscriptionMapper.subscriptionDTOToSubscription((SubscriptionDTO)any())).thenReturn(subcription);
		when(subscriptionRepository.save((Subscription) any())).thenReturn(subcription);
		when(subscriptionMapper.subscriptionToSubscriptionDTO((Subscription) any())).thenReturn(subscriptionDTO);
		
		// Then
		assertThat(subscriptionServiceImpl.save(subscriptionDTO)).isNotNull();
	}
}
