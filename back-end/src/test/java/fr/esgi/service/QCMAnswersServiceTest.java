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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fr.esgi.dao.QCMAnswersRepository;
import fr.esgi.service.dto.QCMAnswersDTO;
import fr.esgi.service.impl.QCMAnswersServiceImpl;
import fr.esgi.service.mapper.QCMAnswersMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class QCMAnswersServiceTest {

	private static final String ANSWER = "test";

	private static final long TRICK_ID = 1L;

	private static final long QCM_ID = 1L;

	private static final long ID = 1L;

	@Mock
	private QCMAnswersRepository qCMAnswersRepository;
	
	@Mock
	private QCMAnswersMapper qcmAnswersMapper;
	
	@InjectMocks
	private QCMAnswersServiceImpl qcmanswersserviceimpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldSaveQCMAnswersDTOWhenIsOK() {
		// Given
		QCMAnswersDTO qcmanswersDTO = new QCMAnswersDTO();
		qcmanswersDTO.setId(ID);
		qcmanswersDTO.setQcmId(QCM_ID);
		qcmanswersDTO.setTrickId(TRICK_ID);
		qcmanswersDTO.setAnswer(ANSWER);
		
		// When
		when(qcmanswersserviceimpl.save(mock(QCMAnswersDTO.class))).thenReturn(qcmanswersDTO);
		
		// Then
		assertThat(qcmanswersserviceimpl.save(mock(QCMAnswersDTO.class))).isEqualTo(qcmanswersDTO);
	}
	
	@Test
	public void shouldSaveQCMAnswersDTOWhenIsKO() {
		// Given
		QCMAnswersDTO qcmanswersDTO = null;
		
		// When
		when(qcmanswersserviceimpl.save(mock(QCMAnswersDTO.class))).thenReturn(qcmanswersDTO);
		
		// Then
		assertThat(qcmanswersserviceimpl.save(mock(QCMAnswersDTO.class))).isNull();
	}
	
}
