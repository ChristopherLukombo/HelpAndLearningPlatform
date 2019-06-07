package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.service.dto.SubscriptionDTO;
import fr.esgi.service.impl.SubscriptionServiceImpl;
import fr.esgi.service.mapper.SubscriptionMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class SubscriptionServiceTest {

	private static final long TRICK_ID = 1L;

	private static final long USER_ID = 1L;

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
	}
	
	@Test
	public void shouldsaveSubscriptionWhenIsOK() {
		// Given
		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
		subscriptionDTO.setId(ID);
		subscriptionDTO.setSubscriptionDate(LocalDate.now());
		subscriptionDTO.setUserId(USER_ID);
		subscriptionDTO.setTrickId(TRICK_ID);
		
		// When
		when(subscriptionServiceImpl.saveSubscription(mock(SubscriptionDTO.class))).thenReturn(subscriptionDTO);
		
		// Then
		assertThat(subscriptionServiceImpl.saveSubscription(mock(SubscriptionDTO.class))).isEqualTo(subscriptionDTO);
	}
	
	@Test
	public void shouldsaveSubscriptionWhenIsKO() {
		// Given
		SubscriptionDTO subscriptionDTO = null;
		
		// When
		when(subscriptionServiceImpl.saveSubscription(mock(SubscriptionDTO.class))).thenReturn(subscriptionDTO);
		
		// Then
		assertThat(subscriptionServiceImpl.saveSubscription(mock(SubscriptionDTO.class))).isEqualTo(subscriptionDTO);
	}
}
