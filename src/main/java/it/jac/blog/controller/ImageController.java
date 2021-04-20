package it.jac.blog.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import it.jac.blog.model.Image;
import it.jac.blog.model.ResponseMessage;
import it.jac.blog.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	ImageService imageService;

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		Optional<Image> c = imageService.get(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Image doesn't exists"));
		}
	}

	@Secured("ROLE_WRITER")
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

	@Secured("ROLE_WRITER")
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestBody Image image) {
		try {
			Image update = imageService.update(image, id);
			return ResponseEntity.ok(update);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Image Not Updated!"));
		}
	}

	@Secured("ROLE_WRITER")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseMessage> deleteImage(@PathVariable Long id) {
		try {
			imageService.delete(id);
			return ResponseEntity.ok().body(new ResponseMessage("Image deleted"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Image doesn't exists"));
		}
	}
	
	@GetMapping(path = "/display/{id}")
	public ResponseEntity<byte[]> display(@PathVariable long id) {
		String basedir=System.getProperty("java.io.tmpdir")+"uploads\\";
		Image image=imageService.get(id).get();
		
		File file = new File(basedir + image.getFilename());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try (FileInputStream in= new FileInputStream(file)){
			int i;
			byte[] cache = new byte[1000 * 1024];
			while ((i = in.read(cache)) > -1)
				out.write(cache, 0, i);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<>(out.toByteArray(),headers, HttpStatus.OK);
	}
}
