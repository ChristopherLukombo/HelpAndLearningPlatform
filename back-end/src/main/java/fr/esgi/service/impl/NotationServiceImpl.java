package fr.esgi.service.impl;

import fr.esgi.dao.NotationRepository;
import fr.esgi.domain.Notation;
import fr.esgi.service.NotationService;
import fr.esgi.service.dto.NotationDTO;
import fr.esgi.service.mapper.NotationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("NotationService")
public class NotationServiceImpl implements NotationService {

    private final NotationRepository notationRepository;

    private final NotationMapper notationMapper;

    @Autowired
    public NotationServiceImpl(NotationRepository notationRepository, NotationMapper notationMapper) {
        this.notationRepository = notationRepository;
        this.notationMapper = notationMapper;
    }

	/**
	 * Save a note for a trick
	 * @param notationDTO
	 * @return NotationDTO
	 */
    @Transactional
    @Override
    public NotationDTO save(NotationDTO notationDTO) {
        Notation notation = notationMapper.notationDTOToNotation(notationDTO);
        notation = notationRepository.save(notation);
        return notationMapper.notationToNotationDTO(notation);
    }
}
