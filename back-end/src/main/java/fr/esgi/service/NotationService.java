package fr.esgi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.esgi.exception.HelpAndLearningPlatformException;
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
     * 
     * @param id
     * @return
     * @throws HelpAndLearningPlatformException 
     */
    NotationDTO findOne(Long id) throws HelpAndLearningPlatformException;
}
