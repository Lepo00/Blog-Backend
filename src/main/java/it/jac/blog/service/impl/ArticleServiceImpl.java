package it.jac.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.jac.blog.enums.Category;
import it.jac.blog.enums.Status;
import it.jac.blog.model.Article;
import it.jac.blog.model.User;
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
	public List<Article> getFirst7ArticleLimit() {
		return articleRepository.findFirst7ByStatusNotOrderByIdDesc(Status.PENDING);
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

	@Override
	public List<Article> myArticlesPage(User author, PageRequest page) {
		return articleRepository.findByAuthor(author, page);
	}

	@Override
	public List<Article> getByCategory(Category category, PageRequest page) {
		return articleRepository.findByCategoriesAndStatusNot(category, Status.PENDING, page);
	}

	@Override
	public List<Article> searchByTitle(String title, PageRequest page) {
		return articleRepository.findByTitleContainingAndStatusNot(title, Status.PENDING, page);
	}

	@Override
	public Long searchSize(String title) {
		return articleRepository.countSearch(title, Status.PENDING);
	}

	@Override
	public Long categorySize(Category category) {
		return (long) articleRepository
				.findByCategoriesAndStatusNot(category, Status.PENDING, PageRequest.of(0, Integer.MAX_VALUE)).size();
	}

}
