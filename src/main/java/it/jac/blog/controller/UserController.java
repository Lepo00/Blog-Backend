package it.jac.blog.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.jac.blog.enums.Role;
import it.jac.blog.enums.Status;
import it.jac.blog.model.Article;
import it.jac.blog.model.Image;
import it.jac.blog.model.ResponseMessage;
import it.jac.blog.model.User;
import it.jac.blog.security.JwtTokenUtil;
import it.jac.blog.service.ArticleService;
import it.jac.blog.service.ImageService;
import it.jac.blog.service.TagService;
import it.jac.blog.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ImageService imageService;
	@Autowired
	TagService tagService;
	@Autowired
	JwtTokenUtil tokenUtil;

	// @Secured("ROLE_ADMIN")
	@PatchMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<User> c = userService.get(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User doesn't exists"));
		}
	}

	@PostMapping
	public ResponseEntity<?> newUser(@RequestBody User user) throws Exception {
		try {
			User save = userService.create(user);
			if (save == null)
				throw new Exception();
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("User Not Saved!"));
		}
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
		try {
			User update = userService.update(user, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("User Not Updated!"));
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseMessage> deleteUser(@PathVariable Long id) {
		try {
			userService.delete(id);
			return ResponseEntity.ok().body(new ResponseMessage("User deleted"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User doesn't exists"));
		}
	}

	@GetMapping(path = "/my-articles")
	public ResponseEntity<?> getMyArticles() {
		User user = tokenUtil.getUserFromToken();
		if (user.getArticles().isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You don't have any articles");
		else
			return ResponseEntity.ok(user.getArticles());
	}

	@PostMapping(path = "/addArticle")
	public ResponseEntity<?> addArticle(@RequestPart Article article, @RequestPart(required = false) MultipartFile image) {
		try {
			User user = tokenUtil.getUserFromToken();
			if (article.getId() != null && articleService.get(article.getId()).isPresent())
				return ResponseEntity.badRequest().body("Article already exists");
			article.setTags(tagService.alreadyExists(article.getTags()));

			if (user.getRole().equals(Role.ADMIN))
				article.setStatus(Status.APPROVED);
			else
				article.setStatus(Status.PENDING);

			if (image != null) {
				imageService.upload(image);
				Image t = new Image();
				t.setFilename(image.getOriginalFilename());
				t.setTitle(image.getOriginalFilename());
				article.setImage(t);
			}

			user.getArticles().add(article);
			articleService.create(article);
			userService.update(user, user.getId());
			return ResponseEntity.ok(article);

		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User not found"));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage("Image not uploaded"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.toString()));
		}
	}

}
