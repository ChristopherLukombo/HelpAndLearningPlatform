package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.esgi.dao.CommentRepository;
import fr.esgi.domain.Comment;
import fr.esgi.domain.Trick;
import fr.esgi.service.dto.CommentDTO;
import fr.esgi.service.impl.CommentServiceImpl;
import fr.esgi.service.mapper.CommentMapper;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

	private static final String NAME = "Bien";

	private static final long ID = 1L;

	@Mock
	private CommentRepository commentRepository;

	@Mock
	private CommentMapper commentMapper;

	@InjectMocks 
	private CommentServiceImpl commentServiceImpl;

	private Comment getComment() {
		Comment comment = new Comment();
		comment.setId(ID);
		comment.setName(NAME);
		comment.setTrick(getTrick());
		return comment;
	}

	private Trick getTrick() {
		Trick trick = new Trick();
		trick.setId(ID);
		return trick;
	}

	private CommentDTO getCommentDTO() {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(ID);
		commentDTO.setName(NAME);
		commentDTO.setTrickId(ID);
		return commentDTO;
	}
	
	@Test
	public void shouldSaveCommentWhenIsOK() {
		// Given
		Comment comment = getComment();

		// When
		when(commentRepository.save((Comment) any())).thenReturn(comment);
		when(commentMapper.commentToCommentDTO(((Comment) any()))).thenReturn(getCommentDTO());

		// Then
		assertThat(commentServiceImpl.save(getCommentDTO())).isNotNull();
	}

	@Test
	public void shouldSaveCommentWhenIsKO() {
		// Given
		Comment comment = null;

		// When
		when(commentRepository.save((Comment) any())).thenReturn(comment);
		when(commentMapper.commentToCommentDTO(((Comment) any()))).thenReturn(null);

		// Then
		assertThat(commentServiceImpl.save(null)).isNull();
	}

	@Test
	public void shouldUpdateCommentWhenIsOK() {
		// Given
		Comment comment = getComment();

		// When
		when(commentRepository.saveAndFlush((Comment) any())).thenReturn(comment);
		when(commentMapper.commentToCommentDTO(((Comment) any()))).thenReturn(getCommentDTO());

		// Then
		assertThat(commentServiceImpl.update(getCommentDTO())).isNotNull();
	}

	@Test
	public void shouldUpdateCommentWhenIsKO() {
		// Given
		CommentDTO commentDTO = null;

		// When
		when(commentMapper.commentToCommentDTO(((Comment) any()))).thenReturn(commentDTO);

		// Then
		assertThat(commentServiceImpl.update(commentDTO)).isNull();
	}
	
	@Test
	public void shouldFindCommentsWhenIsOK() {
		// Given
		Comment comment = getComment();
		List<Comment> comments = new ArrayList<Comment>();
		comments.add(comment);

		// When
		when(commentRepository.findAll()).thenReturn(comments);
		when(commentMapper.commentToCommentDTO(((Comment) any()))).thenReturn(getCommentDTO());

		// Then
		assertThat(commentServiceImpl.findAll()).isNotEmpty();
	}
	
	@Test
	public void shouldFindCommentsWhenIsEmpty() {
		// Given
		List<Comment> comments = new ArrayList<Comment>();

		// When
		when(commentRepository.findAll()).thenReturn(comments);

		// Then
		assertThat(commentServiceImpl.findAll()).isEmpty();
	}
	
	@Test
	public void shouldFindCommentsWhenIsKO() {
		// Given
		List<Comment> comments = null;

		// When
		when(commentRepository.findAll()).thenReturn(comments);

		// Then
		assertThatThrownBy(() -> commentServiceImpl.findAll())
		.isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void shouldFindOneWhenIsOK() {
		// Given
		Comment comment = getComment();
		
		// When
		when(commentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));
		when(commentMapper.commentToCommentDTO(((Comment) any()))).thenReturn(getCommentDTO());
		
		// Then
		assertThat(commentServiceImpl.findOne(ID)).isNotEqualTo(Optional.empty());
	}
	
	@Test
	public void shouldFindOneWhenIsKO() {
		// Given
		Comment comment = null;
		
		// When
		when(commentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));
		
		// Then
		assertThat(commentServiceImpl.findOne(ID)).isEqualTo(Optional.empty());
	}
	
	@Test
	public void shouldDeleteWhenIsOK() {
		// Given
		doNothing().when(commentRepository).deleteById(anyLong());
		
		// When
		commentServiceImpl.delete(1L);
		
		// Then
		 verify(commentRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	public void shouldFindCommentsByTrickIdWhenIsOK() {
		// Given
		Comment comment = getComment();
		List<Comment> comments = new ArrayList<Comment>();
		comments.add(comment);

		// When
		when(commentRepository.findAllByTrickId(anyLong())).thenReturn(comments);
		when(commentMapper.commentToCommentDTO(((Comment) any()))).thenReturn(getCommentDTO());

		// Then
		assertThat(commentServiceImpl.findAllByTrickId(ID)).isNotEmpty();
	}
	
	@Test
	public void shouldFindCommentsByTrickIdWhenIsEmpty() {
		// Given
		List<Comment> comments = new ArrayList<Comment>();

		// When
		when(commentRepository.findAllByTrickId(anyLong())).thenReturn(comments);

		// Then
		assertThat(commentServiceImpl.findAllByTrickId(ID)).isEmpty();
	}
	
	@Test
	public void shouldFindCommentsByTrickIdWhenIsKO() {
		// Given
		List<Comment> comments = null;

		// When
		when(commentRepository.findAllByTrickId(anyLong())).thenReturn(comments);

		// Then
		assertThatThrownBy(() -> commentServiceImpl.findAllByTrickId(ID))
		.isInstanceOf(NullPointerException.class);
	}
}
