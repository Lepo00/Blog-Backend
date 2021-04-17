package it.jac.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.jac.blog.model.Article;

@Repository("articleRepository")
public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	@Query(value="SELECT * FROM Article ORDER BY id DESC LIMIT ?1", nativeQuery = true)
	List<Article> findFirstArticlesLimit(Long limit);
	
}
