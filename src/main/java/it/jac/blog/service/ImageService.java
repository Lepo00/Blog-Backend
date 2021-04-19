package it.jac.blog.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import it.jac.blog.model.Image;

public interface ImageService {
	public Optional<Image> get(Long id);

	public Image create(Image image);

	public void createAll(List<Image> images);

	public void delete(Long id);

	public Image update(Image image, Long id);

	public void upload(MultipartFile image) throws IOException;

}
