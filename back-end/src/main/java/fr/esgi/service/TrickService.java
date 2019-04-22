package fr.esgi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.TrickDTO;

@Service
public interface TrickService {

	/**
     * Find all new tricks which are available according to the id
	 * @param userId 
	 * @return list of entities
     */
    List<TrickDTO> findAllNewTricksAvailableByUserId(Long userId);
}
