package fr.esgi.service;

import fr.esgi.service.dto.TrickDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrickService {

    List<TrickDTO> findAllNewTricksAvailableByUserId(Long userId);
}
