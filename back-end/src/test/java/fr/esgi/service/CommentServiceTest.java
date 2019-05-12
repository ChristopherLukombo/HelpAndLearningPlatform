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

import fr.esgi.dao.CommentRepository;
import fr.esgi.service.dto.CommentDTO;
import fr.esgi.service.impl.CommentServiceImpl;
import fr.esgi.service.mapper.CommentMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class CommentServiceTest {

	private static final long TRICK_ID = 1L;

	private static final String NAME = "Bien";

	private static final long ID = 1L;

	@Mock
	private CommentRepository commentRepository;

	@Mock
	private CommentMapper commentMapper;
	
	@InjectMocks 
	private CommentServiceImpl commentServiceImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldSaveCommentWhenIsOK() {
		// Given
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(ID);
		commentDTO.setName(NAME);
		commentDTO.setTrickId(TRICK_ID);
		
		// When
		when(commentServiceImpl.save(mock(CommentDTO.class))).thenReturn(commentDTO);
		
		// Then
		assertThat(commentServiceImpl.save(mock(CommentDTO.class))).isEqualTo(commentDTO);
	}
	
	@Test
	public void shouldSaveCommentWhenIsKO() {
		// Given
		CommentDTO commentDTO = null;
		
		// When
		when(commentServiceImpl.save(mock(CommentDTO.class))).thenReturn(commentDTO);
		
		// Then
		assertThat(commentServiceImpl.save(mock(CommentDTO.class))).isEqualTo(commentDTO);
	}
}
