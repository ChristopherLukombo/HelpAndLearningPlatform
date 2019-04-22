package fr.esgi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.esgi.domain.QCMAnswers;

@Repository
public interface QCMAnswersRepository extends JpaRepository<QCMAnswers, Long> {
}
