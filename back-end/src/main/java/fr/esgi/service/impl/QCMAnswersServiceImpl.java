package fr.esgi.service.impl;

import fr.esgi.dao.QCMAnswersRepository;
import fr.esgi.domain.QCMAnswers;
import fr.esgi.service.QCMAnswersService;
import fr.esgi.service.dto.QCMAnswersDTO;
import fr.esgi.service.mapper.QCMAnswersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing QCMAnswers.
 */
@Service("QCMAnswersService")
@Transactional
public class QCMAnswersServiceImpl implements QCMAnswersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QCMAnswersServiceImpl.class);

    private final QCMAnswersRepository qcmAnswersRepository;

    private final QCMAnswersMapper qcmAnswersMapper;

    @Autowired
    public QCMAnswersServiceImpl(QCMAnswersRepository qcmAnswersRepository, QCMAnswersMapper qcmAnswersMapper) {
        this.qcmAnswersRepository = qcmAnswersRepository;
        this.qcmAnswersMapper = qcmAnswersMapper;
    }

    /**
	 * Save answers to a QCM link to trick
	 * @param qcmAnswersDTO the entity to save
	 * @return qcmAnswersDTO the persisted entity
	 */
    @Override
    public QCMAnswersDTO save(QCMAnswersDTO qcmAnswersDTO) {
        LOGGER.debug("Request to save a QCMAnswers: {}", qcmAnswersDTO);
        QCMAnswers qcmAnswers = qcmAnswersMapper.qcmanswersDTOToqcmanswers(qcmAnswersDTO);
        qcmAnswers = qcmAnswersRepository.save(qcmAnswers);
        return qcmAnswersMapper.qcmanswersToQcmanswersDTO(qcmAnswers);
    }
}
