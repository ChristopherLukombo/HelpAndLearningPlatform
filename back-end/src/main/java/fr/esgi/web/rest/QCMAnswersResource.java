package fr.esgi.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.service.QCMAnswersService;
import fr.esgi.service.dto.QCMAnswersDTO;
import io.swagger.annotations.Api;
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
     * POST  /qcmanswers : reply to QCM
     * @param qcmAnswersDTO 
     *
     * @return the ResponseEntity with status 201 (OK) and the qcmanswers in body
     * @throws URISyntaxException 
     */
    @PostMapping(value = "/qcmanswers")
    public ResponseEntity<Object> createQCMAnswers(@RequestBody @Valid QCMAnswersDTO qcmAnswersDTO) throws URISyntaxException {
		LOGGER.debug("REST request to save a QCM: {}", qcmAnswersDTO);
    	
		QCMAnswersDTO qcmAnswers = qcmAnswersService.save(qcmAnswersDTO);
        
		return ResponseEntity.created(new URI("/qcmanswers/" + qcmAnswers.getId()))
                .build();
    }
}
