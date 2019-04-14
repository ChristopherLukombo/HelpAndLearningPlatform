package fr.esgi.service;

import fr.esgi.service.dto.StatsDTO;
import org.springframework.stereotype.Service;

@Service
public interface StatsService {

    StatsDTO getStatsForTrick(Long userId, Long categoryId);
}
