package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import fr.esgi.domain.Subscription;
import fr.esgi.service.dto.SubscriptionDTO;
import fr.esgi.service.impl.SubscriptionServiceImpl;
import fr.esgi.service.mapper.SubscriptionMapper;

@Profile("test")
@RunWith(MockitoJUnitRunner.Silent.class)
public class SubscriptionServiceTest {

	private static final long ID = 1L;

	@Mock
	private SubscriptionRepository subscriptionRepository;

	@Mock
	private SubscriptionMapper subscriptionMapper;
	
	@InjectMocks
	private SubscriptionServiceImpl subscriptionServiceImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		subscriptionServiceImpl = new SubscriptionServiceImpl(subscriptionRepository, subscriptionMapper);
	}
	
	private static Subscription getSubscription() {
		Subscription subscription = new Subscription();
		subscription.setId(ID);
		subscription.setSubscriptionDate(LocalDate.now());
		return subscription;
	}
	
	private static SubscriptionDTO getSubscriptionDTO() {
		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
		subscriptionDTO.setId(ID);
		subscriptionDTO.setSubscriptionDate(LocalDate.now());
		return subscriptionDTO;
	}
	
	@Test
	public void shouldSaveSubscriptionWhenIsOK() {
		// Given
		Subscription subscription = getSubscription();
		SubscriptionDTO subscriptionDTO = getSubscriptionDTO();
		
		// When
		when(subscriptionRepository.save(mock(Subscription.class))).thenReturn(subscription);
		when(subscriptionServiceImpl.saveSubscription(mock(SubscriptionDTO.class))).thenReturn(subscriptionDTO);
		
		// Then
		assertThat(subscriptionServiceImpl.saveSubscription(mock(SubscriptionDTO.class))).isNotNull();
	}
	
	@Test
	public void shouldSaveSubscriptionWhenIsKO() {
		// Given
		Subscription subscription = null;
		
		// When
		when(subscriptionRepository.save(mock(Subscription.class))).thenReturn(subscription);
		
		// Then
		assertThat(subscriptionServiceImpl.saveSubscription(mock(SubscriptionDTO.class))).isNull();
	}
	
	@Test
	public void shouldFindAllWhenIsOK() {
		// Given
		Subscription subcription = getSubscription();
		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(subcription);

		// When
		when(subscriptionRepository.findAll()).thenReturn(subscriptions);
		
		// Then
		assertThat(subscriptionServiceImpl.findAll()).isNotEmpty();
	}
	
	@Test
	public void shouldFindAllWhenIsEmpty() {
		// Given
		List<Subscription> subscriptions = new ArrayList<>();

		// When
		when(subscriptionRepository.findAll()).thenReturn(subscriptions);

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
}
