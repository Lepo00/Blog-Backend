package it.jac.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
