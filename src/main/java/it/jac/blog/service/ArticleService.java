package it.jac.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;

import it.jac.blog.enums.Category;
import it.jac.blog.model.Article;
import it.jac.blog.model.User;

public interface ArticleService {
	public Optional<Article> get(Long id);

	public List<Article> getFirstArticleLimit(Long limit);
	
	public Article create(Article article);

	public void createAll(List<Article> articles);

	public void delete(Long id);

	public Article update(Article article, Long id);
	
	public List<Article> myArticlesPage(User author, PageRequest page);
	
	public List<Article> getByCategory(Category category, PageRequest page);
	
	public List<Article> searchByTitle(String title, PageRequest page);
	
	public Long searchSize(String title);
	
}
