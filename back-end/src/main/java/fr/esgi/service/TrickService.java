package fr.esgi.service;

import java.util.List;

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
     * Find all tricks by userId.
	 * @param userId 
	 * @return list of entities
     */
    List<TrickDTO> findAllByUserId(Long userId);

	/**
     * Find all new tricks which are available according to the id
	 * @param userId 
	 * @return list of entities
     */
    List<TrickDTO> findAllNewTricksAvailableByUserId(Long userId);
    
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

}
