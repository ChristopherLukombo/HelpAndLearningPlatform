package fr.esgi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.esgi.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneWithAuthoritiesByLogin(String lowercaseLogin);

    Optional<User> findOneWithAuthoritiesByEmail(String lowercaseLogin);

    Optional<User> findOneByLogin(String toLowerCase);

    Optional<User> findOneByEmailIgnoreCase(String email);

}
