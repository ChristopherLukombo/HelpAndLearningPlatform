package fr.esgi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.esgi.domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s FROM Subscription s WHERE s.user.id = :userId")
    List<Subscription> findAllByUser(@Param("userId") Long userId);

    @Query("SELECT COUNT(s) FROM Subscription s WHERE s.category.id = :categoryId")
    Long findNumberSubscriptions(@Param("categoryId") Long categoryId);
}
