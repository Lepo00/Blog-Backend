package it.jac.blog.service;

import java.util.List;
import java.util.Optional;

import it.jac.blog.model.Article;

public interface ArticleService {
	public Optional<Article> get(Long id);

	public List<Article> getFirstArticleLimit(Long limit);
	
	public Article create(Article article);

	public void createAll(List<Article> articles);

	public void delete(Long id);

	public Article update(Article article, Long id);
	

}
