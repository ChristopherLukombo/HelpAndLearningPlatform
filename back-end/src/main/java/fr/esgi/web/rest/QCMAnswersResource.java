package fr.esgi.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import fr.esgi.exception.HelpAndLearningPlatformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.service.QCMAnswersService;
import fr.esgi.service.dto.QCMAnswersDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing QCMAnswers.
 * @author christopher
 */
@Api(value = "QCMAnswers")
@RestController
@RequestMapping("/api")
public class QCMAnswersResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(QCMAnswersResource.class);

    private final QCMAnswersService qcmAnswersService;

    @Autowired
    public QCMAnswersResource(QCMAnswersService qcmAnswersService) {
        this.qcmAnswersService = qcmAnswersService;
    }

    /**
     * POST  /qcmanswers : reply to a QCM.
     * @param qcmAnswersDTO the ResponseEntity with status 201 (Created)
     * @return the ResponseEntity with status 201 (OK) and the qcmanswers in body
     * @throws URISyntaxException URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "Reply to a QCM.")
    @PostMapping(value = "/qcmanswers")
    public ResponseEntity<QCMAnswersDTO> createQCMAnswers(@RequestBody @Valid QCMAnswersDTO qcmAnswersDTO) throws URISyntaxException, HelpAndLearningPlatformException {
		LOGGER.debug("REST request to save a QCM: {}", qcmAnswersDTO);
        if (null != qcmAnswersDTO.getId()) {
            throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
                    "A new qcmAnswers cannot already have an ID");
        }
		QCMAnswersDTO qcmAnswers = qcmAnswersService.save(qcmAnswersDTO);
		return ResponseEntity.created(new URI("/qcmanswers/" + qcmAnswers.getId()))
				.body(qcmAnswers);
    }
}
