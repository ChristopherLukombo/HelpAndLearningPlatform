package fr.esgi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.CommentDTO;

/**
 * Service Implementation for managing Comment.
 */
@Service
public interface CommentService {

	/**
	 * Save a comment for a trick.
	 * 
	 * @param commentDTO
	 * @return CommentDTO
	 */
	CommentDTO save(CommentDTO commentDTO);

	/**
	 * Update a comment for a trick.
	 * 
	 * @param commentDTO
	 * @return CommentDTO
	 */
	CommentDTO update(CommentDTO commentDTO);

	/**
	 * Get all the comments.
	 * 
	 * @return the list of entity
	 */
	List<CommentDTO> findAll();

	/**
	 * Get the comment by id.
	 * 
	 * @param id : the id of the comment.
	 * @return the entity
	 */
	Optional<CommentDTO> findOne(Long id);

	/**
	 * Delete the comment by id.
	 * 
	 * @param id : the id of entity.
	 */
	void delete(Long id);
}
