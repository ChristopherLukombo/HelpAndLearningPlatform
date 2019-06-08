package fr.esgi.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

import fr.esgi.dao.NotationRepository;
import fr.esgi.domain.Notation;
import fr.esgi.service.dto.NotationDTO;
import fr.esgi.service.impl.NotationServiceImpl;
import fr.esgi.service.mapper.NotationMapper;

@Profile("test")
@RunWith(MockitoJUnitRunner.Silent.class)
public class NotationServiceTest {

	private static final long TRICK_ID = 1L;

	private static final int NOTE = 1;

	private static final long ID = 1L;

	@Mock
	private NotationRepository notationRepository;

	@Mock
	private NotationMapper notationMapper;

	@InjectMocks
	private NotationServiceImpl notationServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		notationServiceImpl = new NotationServiceImpl(notationRepository, notationMapper);
	}

	@Test
	public void shouldSaveNotationWhenIsOK() {
		// Given
		NotationDTO notationDTO = new NotationDTO();
		notationDTO.setId(ID);
		notationDTO.setNote(NOTE);
		notationDTO.setTrickId(TRICK_ID);

		// When
		when(notationServiceImpl.save(mock(NotationDTO.class))).thenReturn(notationDTO);

		// Then
		assertThat(notationServiceImpl.save(mock(NotationDTO.class))).isEqualTo(notationDTO);
	}

	@Test
	public void shouldSaveNotationWhenIsKO() {
		// Given
		NotationDTO notationDTO = null;

		// When
		when(notationServiceImpl.save(mock(NotationDTO.class))).thenReturn(null);

		// Then
		assertThat(notationServiceImpl.save(mock(NotationDTO.class))).isEqualTo(notationDTO);
	}
	
	@Test
	public void shouldFindAllByTrickIdWhenIsOK() {
		// Given
		List<Notation> notations = new ArrayList<>();
        notations.add(new Notation());
		
		// When
		when(notationRepository.findAllByTrickId(anyLong())).thenReturn(notations);
		
		// Then
		assertThat(notationServiceImpl.findAllByTrickId(anyLong())).isNotEmpty();
	}
	
	@Test
	public void shouldFindAllByTrickIdWhenIsEmpty() {
		// Given
		List<Notation> notations = new ArrayList<>();
		
		// When
		when(notationRepository.findAllByTrickId(anyLong())).thenReturn(notations);
		
		// Then
		assertThat(notationServiceImpl.findAllByTrickId(anyLong())).isEmpty();
	}
	
	@Test
	public void shouldFindAllByTrickIdWhenIsKO() {
		// Given
		List<Notation> notations = null;
		
		// When
		when(notationRepository.findAllByTrickId(anyLong())).thenReturn(notations);
		
		// Then
		assertThatThrownBy(() -> notationServiceImpl.findAllByTrickId(anyLong()))
		.isInstanceOf(NullPointerException.class);
	}
}
