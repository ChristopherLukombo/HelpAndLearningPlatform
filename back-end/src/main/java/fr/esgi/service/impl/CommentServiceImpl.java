package fr.esgi.service.impl;

import fr.esgi.dao.CommentRepository;
import fr.esgi.domain.Comment;
import fr.esgi.service.CommentService;
import fr.esgi.service.dto.CommentDTO;
import fr.esgi.service.mapper.CommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
