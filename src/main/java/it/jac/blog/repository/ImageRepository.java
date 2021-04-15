package it.jac.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jac.blog.model.Image;

@Repository("imageRepository")
public interface ImageRepository extends JpaRepository<Image, Long> {
}
