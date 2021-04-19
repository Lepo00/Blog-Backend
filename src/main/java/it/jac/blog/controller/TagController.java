package it.jac.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.blog.model.ResponseMessage;
import it.jac.blog.model.Tag;
import it.jac.blog.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired
	TagService tagService;

	// @Secured("ROLE_ADMIN")
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<Tag> c = tagService.get(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Tag doesn't exists"));
		}
	}

	@PostMapping
	public ResponseEntity<?> newTag(@RequestBody Tag tag) throws Exception {
		try {
			Tag save = tagService.create(tag);
			if (save == null)
				throw new Exception();
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Tag Not Saved!"));
		}
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
		try {
			Tag update = tagService.update(tag, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Tag Not Updated!"));
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseMessage> deleteTag(@PathVariable Long id) {
		try {
			tagService.delete(id);
			return ResponseEntity.ok().body(new ResponseMessage("Tag deleted"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Tag doesn't exists"));
		}
	}
}
