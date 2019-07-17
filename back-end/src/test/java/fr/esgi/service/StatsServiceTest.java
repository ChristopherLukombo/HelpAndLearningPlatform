package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.esgi.dao.NotationRepository;
import fr.esgi.dao.SubscriptionRepository;
import fr.esgi.dao.TrickRepository;
import fr.esgi.domain.Category;
import fr.esgi.domain.Notation;
import fr.esgi.domain.Trick;
import fr.esgi.service.impl.StatsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class StatsServiceTest {

	private static final int NOTE = 1;

	private static final String DESCRIPTION = "Blo blob";

	private static final String WORDING = "Computer Science";

	private static final long ID = 1L;

	@Mock
	private TrickRepository trickRepository;

	@Mock
	private NotationRepository notationRepository;

	@Mock
	private SubscriptionRepository subscriptionRepository;

	@InjectMocks
	private StatsServiceImpl statsServiceImpl;

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

	private static Notation getNotation() {
		Notation notation = new Notation();
		notation.setId(ID);
		notation.setNote(NOTE);
		notation.setTrick(getTrick());
		return notation;
	}

	private static Category getCategory() {
		Category category = new Category();
		category.setId(ID);
		category.setWording(WORDING);
		return category;
	}

	@Test
	public void shouldGetStatsTricksForOwnerWhenIsOK() {
		// Given
		List<Trick> tricks = new ArrayList<>();
		tricks.add(getTrick());

		List<Notation> notations = new ArrayList<>();
		notations.add(getNotation());

		// When
		when(trickRepository.findAllByOwnUserId(anyLong())).thenReturn(tricks);
		when(notationRepository.findAllByTrickId(anyLong())).thenReturn(notations);
		when(subscriptionRepository.findNumberSubscriptionsByCategoryId(anyLong())).thenReturn(1L);

		// Then
		assertThat(statsServiceImpl.getStatsTricksForOwner(ID, ID)).isNotNull();
	}

	@Test
	public void shouldGetStatsTricksForOwnerWhenTrickIsNull() {
		// Given
		List<Trick> tricks = null;

		// When
		when(trickRepository.findAllByOwnUserId(anyLong())).thenReturn(tricks);

		// Then
		assertThat(statsServiceImpl.getStatsTricksForOwner(ID, ID)).isNull();
	}

	@Test
	public void shouldGetStatsTricksForOwnerWhenIsEmpty() {
		// Given
		List<Trick> tricks = new ArrayList<>();

		// When
		when(trickRepository.findAllByOwnUserId(anyLong())).thenReturn(tricks);

		// Then
		assertThat(statsServiceImpl.getStatsTricksForOwner(ID, ID)).isNull();
	}

	@Test
	public void shoulGetStatsForTrickWhenIsOK() {
		// Given
		List<Notation> notations = new ArrayList<>();
		notations.add(getNotation());

		// When
		when(notationRepository.findAllByTrickId(anyLong())).thenReturn(notations);

		// Then
		assertThat(statsServiceImpl.getStatsForTrick(ID)).isNotNull();
	}

	@Test
	public void shoulGetStatsForTrickWhenIsNull() {
		// Given
		List<Notation> notations = null;

		// When
		when(notationRepository.findAllByTrickId(anyLong())).thenReturn(notations);

		// Then
		assertThatThrownBy(() -> statsServiceImpl.getStatsForTrick(ID))
		.isInstanceOf(NullPointerException.class);
	}
}
