package fr.esgi.service;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.CommentDTO;

@Service
public interface CommentService {

	/**
	 * Save a comment for a trick
	 * @param commentDTO
	 * @return CommentDTO
	 */
    CommentDTO save(CommentDTO commentDTO);
}
