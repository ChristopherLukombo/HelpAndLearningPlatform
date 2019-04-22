package fr.esgi.service.impl;

import fr.esgi.dao.QCMAnswersRepository;
import fr.esgi.domain.QCMAnswers;
import fr.esgi.service.QCMAnswersService;
import fr.esgi.service.dto.QCMAnswersDTO;
import fr.esgi.service.mapper.QCMAnswersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("QCMAnswersService")
public class QCMAnswersServiceImpl implements QCMAnswersService {

    private final QCMAnswersRepository qcmAnswersRepository;

    private final QCMAnswersMapper qcmAnswersMapper;

    @Autowired
    public QCMAnswersServiceImpl(QCMAnswersRepository qcmAnswersRepository, QCMAnswersMapper qcmAnswersMapper) {
        this.qcmAnswersRepository = qcmAnswersRepository;
        this.qcmAnswersMapper = qcmAnswersMapper;
    }

    /**
	 * Save answers to a QCM link to trick
	 * @param qcmAnswersDTO
	 * @return qcmAnswersDTO
	 */
    @Transactional
    @Override
    public QCMAnswersDTO save(QCMAnswersDTO qcmAnswersDTO) {
        QCMAnswers qcmAnswers = qcmAnswersMapper.qcmanswersDTOToqcmanswers(qcmAnswersDTO);
        qcmAnswers = qcmAnswersRepository.save(qcmAnswers);
        return qcmAnswersMapper.qcmanswersToQcmanswersDTO(qcmAnswers);
    }
}
