package it.jac.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.blog.model.Article;
import it.jac.blog.repository.ArticleRepository;
import it.jac.blog.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Override
	public Optional<Article> get(Long id) {
		return articleRepository.findById(id);
	}

	@Override
	public List<Article> getFirstArticleLimit(Long limit) {
		return articleRepository.findFirstArticlesLimit(limit);
	}

	@Override
	public Article create(Article c) {
		return articleRepository.save(c);
	}

	@Override
	public void createAll(List<Article> articles) {
		articleRepository.saveAll(articles);
	}

	@Override
	public void delete(Long id) {
		articleRepository.deleteById(id);
	}

	@Override
	public Article update(Article article, Long id) {
		return articleRepository.findById(id).map(c -> { // update if entity already exists
			article.setId(c.getId());
			return create(article);
		}).orElseGet(() -> { // create if entity not exists
			return articleRepository.save(article);
		});
	}

}
