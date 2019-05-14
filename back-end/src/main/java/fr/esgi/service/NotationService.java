package fr.esgi.service;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.NotationDTO;

/**
 * Service Implementation for managing Notation.
 */
@Service
public interface NotationService {

	/**
	 * Save a note for a trick
	 * @param notationDTO
	 * @return NotationDTO
	 */
    NotationDTO save(NotationDTO notationDTO);
}
