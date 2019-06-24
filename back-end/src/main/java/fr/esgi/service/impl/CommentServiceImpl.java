package fr.esgi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.dao.CommentRepository;
import fr.esgi.domain.Comment;
import fr.esgi.service.CommentService;
import fr.esgi.service.dto.CommentDTO;
import fr.esgi.service.mapper.CommentMapper;

/**
 * Service Implementation for managing Comment.
 */
@Service("CommentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

	private final CommentRepository commentRepository;

	private final CommentMapper commentMapper;

	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
		this.commentRepository = commentRepository;
		this.commentMapper = commentMapper;
	}

	/**
	 * Save a comment for a trick
	 * @param commentDTO the entity to save
	 * @return CommentDTO the persisted entity
	 */
	@Override
	public CommentDTO save(CommentDTO commentDTO) {
		LOGGER.debug("Request to save a Comment: {}", commentDTO);
		Comment comment = commentMapper.CommentDTOToComment(commentDTO);
		comment = commentRepository.save(comment);
		return commentMapper.commentToCommentDTO(comment);
	}

	/**
	 * Update a comment for a trick
	 * @param commentDTO the entity to save
	 * @return CommentDTO the persisted entity
	 */
	@Override
	public CommentDTO update(CommentDTO commentDTO) {
		LOGGER.debug("Request to update a Comment: {}", commentDTO);
		Comment comment = commentMapper.CommentDTOToComment(commentDTO);
		comment = commentRepository.saveAndFlush(comment);
		return commentMapper.commentToCommentDTO(comment);
	}

	/**
	 * Get all the comments.
	 * 
	 * @return the list of entity
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CommentDTO> findAll() {
		LOGGER.debug("Request to get all comments");
		return commentRepository.findAll().stream()
				.map(commentMapper::commentToCommentDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Get the comment by id.
	 * 
	 * @param id : the id of the comment.
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<CommentDTO> findOne(Long id) {
		LOGGER.debug("Request to get comment : {}", id);
		return commentRepository.findById(id)
				.map(commentMapper::commentToCommentDTO);
	}

	/**
	 * Delete the comment by id.
	 * 
	 * @param id : the id of entity.
	 */
	@Override
	public void delete(Long id) {
		LOGGER.debug("Request to delete comment : {}", id);
		commentRepository.deleteById(id);
	}
}
