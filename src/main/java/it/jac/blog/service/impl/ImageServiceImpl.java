package it.jac.blog.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.jac.blog.model.Image;
import it.jac.blog.repository.ImageRepository;
import it.jac.blog.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	ImageRepository imageRepository;

	@Override
	public Optional<Image> get(Long id) {
		return imageRepository.findById(id);
	}

	@Override
	public Image create(Image c) {
		return imageRepository.save(c);
	}

	@Override
	public void createAll(List<Image> images) {
		imageRepository.saveAll(images);
	}

	@Override
	public void delete(Long id) {
		imageRepository.deleteById(id);
	}

	@Override
	public Image update(Image image, Long id) {
		return imageRepository.findById(id).map(c -> { // update if entity already exists
			image.setId(c.getId());
			return create(image);
		}).orElseGet(() -> { // create if entity not exists
			return imageRepository.save(image);
		});
	}

	@Override
	public void upload(MultipartFile image) throws IOException {
		String basedir=System.getProperty("java.io.tmpdir")+"uploads\\"+image.getOriginalFilename();
			if(ImageIO.read(image.getInputStream())!=null) {
				Path path = Paths.get(basedir);
			    Files.write(path, image.getBytes());	
			}
	}
}
