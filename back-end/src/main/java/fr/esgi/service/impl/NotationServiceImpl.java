package fr.esgi.service.impl;

import fr.esgi.dao.NotationRepository;
import fr.esgi.domain.Notation;
import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.NotationService;
import fr.esgi.service.dto.NotationDTO;
import fr.esgi.service.mapper.NotationMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public NotationDTO findOne(Long id) throws HelpAndLearningPlatformException {
		final Optional<Notation> notation = notationRepository.findById(id);
		if (notation.isPresent()) {
			return notationMapper.notationToNotationDTO(notation.get());
		} else {
			throw new HelpAndLearningPlatformException("Notation not found by id");
		}
	}
}
