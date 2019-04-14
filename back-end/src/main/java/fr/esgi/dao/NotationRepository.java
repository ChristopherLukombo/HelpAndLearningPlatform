package fr.esgi.dao;

import fr.esgi.domain.Notation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotationRepository extends JpaRepository<Notation, Long> {
}
