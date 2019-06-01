package fr.esgi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.esgi.domain.Authority;

/**
 * Spring Data JPA repository for the AuthorityRepository entity.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
