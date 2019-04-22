package fr.esgi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.esgi.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
