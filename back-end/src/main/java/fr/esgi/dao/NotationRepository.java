package fr.esgi.dao;

import fr.esgi.domain.Notation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotationRepository extends JpaRepository<Notation, Long> {

    @Query("SELECT n FROM Notation n WHERE n.trick.id = :trickId")
    List<Notation> findAllByTrickId(Long trickId);
}
