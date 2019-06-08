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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

import fr.esgi.dao.CommentRepository;
import fr.esgi.domain.Comment;
import fr.esgi.service.dto.CommentDTO;
import fr.esgi.service.impl.CommentServiceImpl;
import fr.esgi.service.mapper.CommentMapper;

@Profile("test")
@RunWith(MockitoJUnitRunner.Silent.class)
public class CommentServiceTest {

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
		commentServiceImpl = new CommentServiceImpl(commentRepository, commentMapper);
	}
	
	private Comment getComment() {
		Comment comment = new Comment();
		comment.setId(ID);
		comment.setName(NAME);
		return comment;
	}
	
	private CommentDTO getCommentDTO() {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(ID);
		commentDTO.setName(NAME);
		return commentDTO;
	}
	
	@Test
	public void shouldSaveCommentWhenIsOK() {
		// Given
		Comment comment = getComment();
		
		// When
		when(commentRepository.save(mock(Comment.class))).thenReturn(comment);
		when(commentServiceImpl.save(mock(CommentDTO.class))).thenReturn(getCommentDTO());
		
		// Then
		assertThat(commentServiceImpl.save(mock(CommentDTO.class))).isNotNull();
	}


	
	@Test
	public void shouldSaveCommentWhenIsKO() {
		// Given
		Comment comment = null;
		
		// When
		when(commentRepository.save(mock(Comment.class))).thenReturn(comment);
		
		// Then
		assertThat(commentServiceImpl.save(mock(CommentDTO.class))).isNull();
	}
}
