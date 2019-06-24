package fr.esgi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.esgi.domain.Subscription;

/**
 * Spring Data JPA repository for the Subscription entity.
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	
    @Query("SELECT s FROM Subscription s WHERE s.user.id = :userId AND s.finished = :finished")
    List<Subscription> findAllByUserIdAndStatus(@Param("userId") Long userId, @Param("finished") Boolean finished);
    
    @Query("SELECT s FROM Subscription s WHERE s.trick.id = :trickId AND s.user.id = :userId")
    List<Subscription> findAllByTrickId(@Param("trickId") Long trickId, @Param("userId") Long userId);

    @Query("SELECT COUNT(s) FROM Subscription s WHERE s.trick.category.id = :categoryId")
    Long findNumberSubscriptionsByCategoryId(@Param("categoryId") Long categoryId);
    
    @Query("SELECT s FROM Subscription s WHERE s.id = :subscriptionId")
    Subscription findBySubscriptionIdAndUserId(@Param("subscriptionId") Long subscriptionId);
}
