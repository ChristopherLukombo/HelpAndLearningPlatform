package fr.esgi.service;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.StatsDTO;

@Service
public interface StatsService {

	/**
	 * Get the stats for a trick
	 * @param userId
	 * @param categoryId
	 * @return stats
	 */
    StatsDTO getStatsForTrick(Long userId, Long categoryId);
}
