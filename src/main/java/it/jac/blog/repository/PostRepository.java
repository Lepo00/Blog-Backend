package it.jac.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.blog.model.Post;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Long> {
}
