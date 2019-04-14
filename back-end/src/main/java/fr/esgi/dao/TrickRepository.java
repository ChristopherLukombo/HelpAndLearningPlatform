package fr.esgi.dao;

import fr.esgi.domain.Trick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TrickRepository extends JpaRepository<Trick, Long> {

    @Query("SELECT t FROM Trick t WHERE t.creationDate >= :date AND t.category.id = :categoryId")
    List<Trick> findAllNewTricksAvailable(@Param("date") LocalDate date,
                                          @Param("categoryId") Long categoryId);
}
