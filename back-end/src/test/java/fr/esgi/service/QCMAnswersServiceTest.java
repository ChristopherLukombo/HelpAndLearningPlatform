package fr.esgi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

import fr.esgi.dao.QCMAnswersRepository;
import fr.esgi.domain.QCMAnswers;
import fr.esgi.service.dto.QCMAnswersDTO;
import fr.esgi.service.impl.QCMAnswersServiceImpl;
import fr.esgi.service.mapper.QCMAnswersMapper;

@Profile("test")
@RunWith(MockitoJUnitRunner.Silent.class)
public class QCMAnswersServiceTest {

	private static final String ANSWER = "test";

	private static final long ID = 1L;

	@Mock
	private QCMAnswersRepository qcmAnswersRepository;

	@Mock
	private QCMAnswersMapper qcmAnswersMapper;

	@InjectMocks
	private QCMAnswersServiceImpl qcmanswersserviceimpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		qcmanswersserviceimpl = new QCMAnswersServiceImpl(qcmAnswersRepository, qcmAnswersMapper);
	}

	private static QCMAnswersDTO getQCMAnswersDTO() {
		QCMAnswersDTO qcmanswersDTO = new QCMAnswersDTO();
		qcmanswersDTO.setId(ID);
		qcmanswersDTO.setAnswer(ANSWER);
		return qcmanswersDTO;
	}

	private static QCMAnswers getQCMAnswers() {
		QCMAnswers qcmanswers = new QCMAnswers();
		qcmanswers.setId(ID);
		qcmanswers.setAnswer(ANSWER);
		return qcmanswers;
	}

	@Test
	public void shouldSaveQCMAnswersDTOWhenIsOK() {
		// Given
		QCMAnswers qcmanswers = getQCMAnswers();

		// When
		when(qcmAnswersRepository.save(mock(QCMAnswers.class))).thenReturn(qcmanswers);
		when(qcmanswersserviceimpl.save(mock(QCMAnswersDTO.class))).thenReturn(getQCMAnswersDTO());

		// Then
		assertThat(qcmanswersserviceimpl.save(mock(QCMAnswersDTO.class))).isEqualTo(getQCMAnswersDTO());
	}

	@Test
	public void shouldSaveQCMAnswersDTOWhenIsKO() {
		// Given
		QCMAnswers qcmanswers = null;

		// When
		when(qcmAnswersRepository.save(mock(QCMAnswers.class))).thenReturn(qcmanswers);

		// Then
		assertThat(qcmanswersserviceimpl.save(mock(QCMAnswersDTO.class))).isNull();
	}
}
