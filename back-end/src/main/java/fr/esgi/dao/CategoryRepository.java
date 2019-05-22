package fr.esgi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.esgi.domain.Category;

/**
 * Spring Data JPA repository for the Category entity.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.wording LIKE wording")
    List<Category> findCategoriesByWording(@Param("wording") String wording);

}
