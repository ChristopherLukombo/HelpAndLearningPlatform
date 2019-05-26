package fr.esgi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.esgi.domain.Notation;

/**
 * Spring Data JPA repository for the Notation entity.
 */
@Repository
public interface NotationRepository extends JpaRepository<Notation, Long> {

    @Query("SELECT n FROM Notation n WHERE n.trick.id = :trickId")
    List<Notation> findAllByTrickId(@Param("trickId") Long trickId);
}
