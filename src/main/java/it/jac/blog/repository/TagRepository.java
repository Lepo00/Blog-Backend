package it.jac.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.blog.model.Tag;

@Repository("tagRepository")
public interface TagRepository extends JpaRepository<Tag, Long> {
}
