package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.esgi.dao.NotationRepository;
import fr.esgi.service.dto.NotationDTO;
import fr.esgi.service.impl.NotationServiceImpl;
import fr.esgi.service.mapper.NotationMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
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
	}

	@Test
	public void saveSaveNotationWhenIsOK() {
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
	public void saveSaveNotationWhenIsKO() {
		// Given
		NotationDTO notationDTO = null;

		// When
		when(notationServiceImpl.save(mock(NotationDTO.class))).thenReturn(null);

		// Then
		assertThat(notationServiceImpl.save(mock(NotationDTO.class))).isEqualTo(notationDTO);
	}
}
