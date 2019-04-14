package fr.esgi.service;

import fr.esgi.service.dto.QCMAnswersDTO;
import org.springframework.stereotype.Service;

@Service
public interface QCMAnswersService {

    QCMAnswersDTO save(QCMAnswersDTO qcmAnswersDTO);
}
