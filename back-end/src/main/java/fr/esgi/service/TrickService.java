package fr.esgi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.esgi.service.dto.TrickDTO;

/**
 * Service Implementation for managing Trick.
 */
@Service
public interface TrickService {
	
	/**
	 * Find all tricks.
	 * @return list of entities
	 */
	List<TrickDTO> findAll();
	
	/**
     * Find all tricks with status and by user id.
	 * @param userId 
	 * @return list of entities
     */
    List<TrickDTO> findAllByUserIdAndStatus(Long userId, Boolean status);
    
    /**
     * Returns the trick by id.
     * @param id : the id of trick
     * @return trick
     */
    Optional<TrickDTO> findOne(Long id);
    
    /**
     * Find all tricks by user id.
	 * @param userId : the id of the owner of Trick
	 * @return list of entities
     */
    Page<TrickDTO> findAllByOwnUserId(Pageable pageable, Long userId);

	/**
     * Find all new tricks which are available according to the id
	 * @param userId 
	 * @return list of entities
     */
    List<TrickDTO> findAllNewTricksAvailableByUserId(Long userId);
    
    /**
     * Save a Trick
     * @param trick
     * @return entity
     */
    TrickDTO save(TrickDTO trickDTO);
    
    /**
     * Update a Trick
     * @param trick
     * @return entity
     */
    TrickDTO update(TrickDTO trickDTO);
    
    /**
     * Find all the most recent tricks.
     * @return list of entities
     */
    List<TrickDTO> findTheMostLatests();
    
    /**
     * Find all the most viewed tricks.
     * @return list of entities
     */
    List<TrickDTO> findTheMostViewed();

    /**
     * Add view to trick according to trickId
     * @param trickId : the id of trick.
     * @return the entity
     */
    TrickDTO addViewToTrick(Long trickId);
    
    void delete(Long id);
}
