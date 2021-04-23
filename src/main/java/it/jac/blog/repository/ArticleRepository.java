package it.jac.blog.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.jac.blog.enums.Category;
import it.jac.blog.model.Article;
import it.jac.blog.model.User;

@Repository("articleRepository")
public interface ArticleRepository extends JpaRepository<Article, Long> {

	@Query(value = "SELECT * FROM Article ORDER BY id DESC LIMIT ?1", nativeQuery = true)
	List<Article> findFirstArticlesLimit(Long limit);

	List<Article> findByAuthor(User author, Pageable page);

	List<Article> findByCategories(Category category, Pageable page);

	List<Article> findByTitleContaining(String title, Pageable page);

	@Query(value = "SELECT count(*) FROM Article WHERE title like %?1%", nativeQuery = true)
	Long countSearch(String title);

}
