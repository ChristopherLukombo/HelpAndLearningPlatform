package fr.esgi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.dao.NotationRepository;
import fr.esgi.domain.Notation;
import fr.esgi.service.NotationService;
import fr.esgi.service.dto.NotationDTO;
import fr.esgi.service.mapper.NotationMapper;

/**
 * Service Implementation for managing Notation.
 */
@Service("NotationService")
@Transactional
public class NotationServiceImpl implements NotationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotationServiceImpl.class);

    private final NotationRepository notationRepository;

    private final NotationMapper notationMapper;

    @Autowired
    public NotationServiceImpl(NotationRepository notationRepository, NotationMapper notationMapper) {
        this.notationRepository = notationRepository;
        this.notationMapper = notationMapper;
    }

	/**
	 * Save a note for a trick
	 * @param notationDTO the entity to save
	 * @return NotationDTO the persisted entity
	 */
    @Override
    public NotationDTO save(NotationDTO notationDTO) {
        LOGGER.debug("Request to save a Notation: {}", notationDTO);
        Notation notation = notationMapper.notationDTOToNotation(notationDTO);
        notation = notationRepository.save(notation);
        return notationMapper.notationToNotationDTO(notation);
    }

    /**
     * Returns all notations by trickId
     * @param trickId : id of trick
     * @return list of entities
     */
    @Transactional(readOnly = true)
	@Override
	public List<NotationDTO> findAllByTrickId(Long trickId) {
		final List<Notation> notations = notationRepository.findAllByTrickId(trickId);
		return notations.stream()
		    .map(notationMapper::notationToNotationDTO).collect(Collectors.toList());
	}

    /**
     * Find a notation by id
     * @param trickId : id of trick
     * @return the entity
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<NotationDTO> findOne(Long id) {
    	final Optional<Notation> notation = notationRepository.findById(id);
    	return notation.map(notationMapper::notationToNotationDTO);
    }
}
