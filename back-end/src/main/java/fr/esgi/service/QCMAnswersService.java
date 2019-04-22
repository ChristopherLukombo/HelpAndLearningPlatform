package fr.esgi.service;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.QCMAnswersDTO;

@Service
public interface QCMAnswersService {

	/**
	 * Save answers to a QCM link to trick
	 * @param qcmAnswersDTO
	 * @return qcmAnswersDTO
	 */
    QCMAnswersDTO save(QCMAnswersDTO qcmAnswersDTO);
}
