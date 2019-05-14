package fr.esgi.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.esgi.domain.Trick;

/**
 * Spring Data JPA repository for the Trick entity.
 */
@Repository
public interface TrickRepository extends JpaRepository<Trick, Long> {

    @Query("SELECT t FROM Trick t WHERE t.creationDate >= :date AND t.category.id = :categoryId")
    List<Trick> findAllNewTricksAvailableByDateAndCategoryId(@Param("date") LocalDate date,
                                                             @Param("categoryId") Long categoryId);

    @Query("SELECT t FROM Trick t WHERE t.ownUser.id = :userId")
    List<Trick> findAllByOwnUserId(@Param("userId") Long userId);
}
