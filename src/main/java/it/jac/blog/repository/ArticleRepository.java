package it.jac.blog.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.jac.blog.enums.Category;
import it.jac.blog.enums.Status;
import it.jac.blog.model.Article;
import it.jac.blog.model.User;

@Repository("articleRepository")
public interface ArticleRepository extends JpaRepository<Article, Long> {
		
	List<Article> findFirst7ByStatusNotOrderByIdDesc(Status status);

	List<Article> findByAuthor(User author, Pageable page);

	List<Article> findByCategoriesAndStatusNot(Category category, Status status, Pageable page);

	List<Article> findByTitleContainingAndStatusNot(String title, Status status, Pageable page);

	@Query(value = "SELECT count(*) FROM Article WHERE title like %?1% AND status <> ?2", nativeQuery = true)
	Long countSearch(String title, Status status);

}
