package fr.esgi.service;

import java.util.List;
import java.util.Optional;

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
    
    /**
     * Returns all notations by trickId
     * @param trickId : id of trick
     * @return list of entities
     */
    List<NotationDTO> findAllByTrickId(Long trickId);
    
    /**
     * Find a notation by id
     * @param trickId : id of trick
     * @return the entity
     */
    Optional<NotationDTO> findOne(Long id);
}
