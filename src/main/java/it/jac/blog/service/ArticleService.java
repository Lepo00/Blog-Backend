package it.jac.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;

import it.jac.blog.enums.Category;
import it.jac.blog.model.Article;
import it.jac.blog.model.User;

public interface ArticleService {
	public Optional<Article> get(Long id);

	public List<Article> getFirst7ArticleLimit();
	
	public Article create(Article article);

	public void createAll(List<Article> articles);

	public void delete(Long id);

	public Article update(Article article, Long id);
	
	public List<Article> getMyArticlesPage(User author, PageRequest page);
	
	public List<Article> getByCategory(Category category, PageRequest page);
	
	public Long categorySize(Category category);
	
	public List<Article> getSearchByTitle(String title, PageRequest page);
	
	public Long searchSize(String title);
	
	public List<Article> getPendingArticles();
	
	public void approveArticle(Long id);
	
}
