package it.jac.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.blog.model.ResponseMessage;
import it.jac.blog.model.Comment;
import it.jac.blog.service.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {
	
	@Autowired
	CommentService commentService;

	//@Secured("ROLE_ADMIN")
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<Comment> c = commentService.get(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Comment doesn't exists"));
		}
	}

	@PostMapping
	public ResponseEntity<?> newComment(@RequestBody Comment comment) throws Exception {
		try {
			Comment save = commentService.create(comment);
			if (save == null)
				throw new Exception();
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Comment Not Saved!"));
		}
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
		try {
			Comment update = commentService.update(comment, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Comment Not Updated!"));
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseMessage> deleteComment(@PathVariable Long id) {
		try {
			commentService.delete(id);
			return ResponseEntity.ok().body(new ResponseMessage("Comment deleted"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Comment doesn't exists"));
		}
	}
}
