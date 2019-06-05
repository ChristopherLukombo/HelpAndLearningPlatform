package fr.esgi.service;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.StatsDTO;
import fr.esgi.service.dto.StatsTrickDTO;

/**
 * Service Implementation for managing Stats.
 */
@Service
public interface StatsService {

	/**
	 * Get the stats for a trick
	 * @param userId
	 * @param categoryId
	 * @return the entity stats
	 */
    StatsDTO getStatsTricksForOwner(Long userId, Long categoryId);
    
    /**
     * Get stat for a trick according trickId.
     * @param trickId
     * @return the entity stats 
     */
    StatsTrickDTO getStatsForTrick(Long trickId);
}
