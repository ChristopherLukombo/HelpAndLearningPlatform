package fr.esgi.dao;

import fr.esgi.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.wording LIKE wording")
    Page<Category> findCategoriesByWording(@Param("wording") String wording, Pageable pageable);

    @Query("SELECT c FROM Category c")
    Page<Category> findAll(Pageable pageable);

}
