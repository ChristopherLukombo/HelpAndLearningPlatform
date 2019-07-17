package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.esgi.dao.NotationRepository;
import fr.esgi.domain.Notation;
import fr.esgi.domain.Trick;
import fr.esgi.service.dto.NotationDTO;
import fr.esgi.service.impl.NotationServiceImpl;
import fr.esgi.service.mapper.NotationMapper;

@RunWith(MockitoJUnitRunner.class)
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
	
	private static NotationDTO getNotationDTO() {
		NotationDTO notationDTO = new NotationDTO();
		notationDTO.setId(ID);
		notationDTO.setNote(NOTE);
		notationDTO.setTrickId(TRICK_ID);
		return notationDTO;
	}
	
	private static Notation getNotation() {
		Notation notation = new Notation();
		notation.setId(ID);
		notation.setNote(NOTE);
		notation.setTrick(getTrick());
		return notation;
	}

	private static Trick getTrick() {
		Trick trick = new Trick();
		trick.setId(TRICK_ID);
		return trick;
	}

	@Test
	public void shouldSaveNotationWhenIsOK() {
		// Given
		NotationDTO notationDTO = getNotationDTO();

		// When
		when(notationRepository.save((Notation) any())).thenReturn(getNotation());
		when(notationMapper.notationToNotationDTO(((Notation) any()))).thenReturn(getNotationDTO());

		// Then
		assertThat(notationServiceImpl.save(mock(NotationDTO.class))).isEqualTo(notationDTO);
	}

	

	@Test
	public void shouldSaveNotationWhenIsKO() {
		// Given
		NotationDTO notationDTO = null;

		// When
		when(notationRepository.save((Notation) any())).thenReturn(null);
		when(notationMapper.notationToNotationDTO(((Notation) any()))).thenReturn(notationDTO);

		// Then
		assertThat(notationServiceImpl.save(mock(NotationDTO.class))).isNull();
	}
	
	@Test
	public void shouldUpdateNotationWhenIsOK() {
		// Given
		NotationDTO notationDTO = getNotationDTO();

		// When
		when(notationRepository.saveAndFlush((Notation) any())).thenReturn(getNotation());
		when(notationMapper.notationToNotationDTO(((Notation) any()))).thenReturn(notationDTO);

		// Then
		assertThat(notationServiceImpl.update(mock(NotationDTO.class))).isEqualTo(notationDTO);
	}

	

	@Test
	public void shouldUpdateNotationWhenIsKO() {
		// Given
		NotationDTO notationDTO = null;

		// When
		when(notationRepository.saveAndFlush((Notation) any())).thenReturn(null);
		when(notationMapper.notationToNotationDTO(((Notation) any()))).thenReturn(null);

		// Then
		assertThat(notationServiceImpl.update(mock(NotationDTO.class))).isEqualTo(notationDTO);
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
	
	@Test
	public void shouldFindOneWhenIsOK() {
		// Given
		Notation notation = getNotation();
		
		// When
		when(notationRepository.findById(anyLong())).thenReturn(Optional.ofNullable(notation));
		when(notationMapper.notationToNotationDTO(((Notation) any()))).thenReturn(getNotationDTO());
		
		// Then
		assertThat(notationServiceImpl.findOne(ID)).isNotEqualTo(Optional.empty());
	}
	
	@Test
	public void shouldFindOneWhenIsKO() {
		// Given
		Notation notation = null;
		
		// When
		when(notationRepository.findById(anyLong())).thenReturn(Optional.ofNullable(notation));
		
		// Then
		assertThat(notationServiceImpl.findOne(ID)).isEqualTo(Optional.empty());
	}
}
