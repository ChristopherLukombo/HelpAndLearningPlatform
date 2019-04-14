package fr.esgi.web.rest;

import fr.esgi.service.QCMAnswersService;
import fr.esgi.service.dto.QCMAnswersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class QCMAnswersResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(QCMAnswersResource.class);

    private final QCMAnswersService qcmAnswersService;

    public QCMAnswersResource(QCMAnswersService qcmAnswersService) {
        this.qcmAnswersService = qcmAnswersService;
    }

    /**
     * POST  /qcmanswers : reply to QCM
     *
     * @return the ResponseEntity with status 201 (OK) and the qcmanswers in body
     */
    @PostMapping(value = "/qcmanswers")
    public ResponseEntity createQCMAnswers(@RequestBody @Valid QCMAnswersDTO qcmAnswersDTO) throws URISyntaxException {
        QCMAnswersDTO qcmAnswers = qcmAnswersService.save(qcmAnswersDTO);
        return ResponseEntity.created(new URI("/qcmanswers/" + qcmAnswers.getId()))
                .build();
    }
}
