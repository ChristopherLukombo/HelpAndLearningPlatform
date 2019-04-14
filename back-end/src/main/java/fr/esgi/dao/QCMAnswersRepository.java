package fr.esgi.dao;

import fr.esgi.domain.QCMAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QCMAnswersRepository extends JpaRepository<QCMAnswers, Long> {
}
