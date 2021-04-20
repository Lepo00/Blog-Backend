package it.jac.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import it.jac.blog.model.Article;
import it.jac.blog.model.ResponseMessage;
import it.jac.blog.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<Article> c = articleService.get(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Article doesn't exists"));
		}
	}
	
	@GetMapping("/limit/{n}")
	public ResponseEntity<?> getFirstLimit(@PathVariable Long n) {
		List<Article> c = articleService.getFirstArticleLimit(n);
		if (!c.isEmpty()) {
			return ResponseEntity.ok(c);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Article doesn't exists"));
		}
	}

	@Secured("ROLE_WRITER")
	@PostMapping
	public ResponseEntity<?> newPost(@RequestBody Article article) throws Exception {
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
	public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Article article) {
		try {
			Article update = articleService.update(article, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Article Not Updated!"));
		}
	}

	@Secured("ROLE_WRITER")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseMessage> deletePost(@PathVariable Long id) {
		try {
			articleService.delete(id);
			return ResponseEntity.ok().body(new ResponseMessage("Article deleted"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Article doesn't exists"));
		}
	}
}
