package it.jac.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.jac.blog.enums.Category;
import it.jac.blog.enums.Status;
import it.jac.blog.model.Article;
import it.jac.blog.model.ResponseMessage;
import it.jac.blog.security.JwtTokenUtil;
import it.jac.blog.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	ArticleService articleService;
	@Autowired
	JwtTokenUtil tokenUtil;

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<Article> c = articleService.get(id);
		if (c.isPresent() && c.get().getStatus() == Status.APPROVED
				|| c.get().getAuthor().getUsername() == tokenUtil.getUserFromToken().getUsername()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Article doesn't exists"));
		}
	}

	@GetMapping
	public ResponseEntity<?> getByCategory(@RequestParam Category category, @RequestParam Integer page,
			@RequestParam Integer size) {
		List<Article> articles = articleService.getByCategory(category,
				PageRequest.of(page, size, Sort.by("id").descending()));
		return ResponseEntity.ok(articles);
	}

	@GetMapping(path = "/category-size")
	public ResponseEntity<Long> categorySize(@RequestParam Category category) {
		Long count = articleService.categorySize(category);
		return ResponseEntity.ok(count);
	}

	@GetMapping(path = "/search")
	public ResponseEntity<?> searchByTitle(@RequestParam String title, @RequestParam Integer page,
			@RequestParam Integer size) {
		List<Article> articles = articleService.searchByTitle(title,
				PageRequest.of(page, size, Sort.by("id").descending()));
		return ResponseEntity.ok(articles);
	}

	@GetMapping(path = "/search-size")
	public ResponseEntity<Long> searchSize(@RequestParam String title) {
		Long count = articleService.searchSize(title);
		return ResponseEntity.ok(count);
	}

	@GetMapping("/limit")
	public ResponseEntity<?> getFirstLimit() {
		List<Article> c = articleService.getFirst7ArticleLimit();
		if (!c.isEmpty()) {
			return ResponseEntity.ok(c);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Article doesn't exists"));
		}
	}

	@Secured("ROLE_WRITER")
	@PostMapping
	public ResponseEntity<?> newArticle(@RequestBody Article article) throws Exception {
		try {
			Article save = articleService.create(article);
			if (save == null)
				throw new Exception();
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Article Not Saved!"));
		}
	}

	@Secured("ROLE_WRITER")
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody Article article) {
		try {
			Article update = articleService.update(article, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Article Not Updated!"));
		}
	}

	@Secured("ROLE_REVIEWER")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseMessage> deleteArticle(@PathVariable Long id) {
		try {
			articleService.delete(id);
			return ResponseEntity.ok().body(new ResponseMessage("Article deleted"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Article doesn't exists"));
		}
	}

}
