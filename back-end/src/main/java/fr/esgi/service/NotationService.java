package fr.esgi.service;

import fr.esgi.service.dto.NotationDTO;
import org.springframework.stereotype.Service;

@Service
public interface NotationService {

    NotationDTO save(NotationDTO notationDTO);
}
