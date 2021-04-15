package it.jac.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.blog.model.Comment;

@Repository("commentRepository")
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
