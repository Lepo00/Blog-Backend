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

import it.jac.blog.model.Image;
import it.jac.blog.model.ResponseMessage;
import it.jac.blog.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	ImageService imageService;

	//@Secured("ROLE_ADMIN")
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<Image> c = imageService.get(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Image doesn't exists"));
		}
	}

	@PostMapping
	public ResponseEntity<?> newImage(@RequestBody Image image) throws Exception {
		try {
			Image save = imageService.create(image);
			if (save == null)
				throw new Exception();
			return ResponseEntity.ok(save);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Image Not Saved!"));
		}
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestBody Image image) {
		try {
			Image update = imageService.update(image, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Image Not Updated!"));
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseMessage> deleteImage(@PathVariable Long id) {
		try {
			imageService.delete(id);
			return ResponseEntity.ok().body(new ResponseMessage("Image deleted"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Image doesn't exists"));
		}
	}
}
