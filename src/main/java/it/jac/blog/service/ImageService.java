package it.jac.blog.service;

import java.util.List;
import java.util.Optional;

import it.jac.blog.model.Image;

public interface ImageService {
	public Optional<Image> get(Long id);

	public Image create(Image image);

	public void createAll(List<Image> images);

	public void delete(Long id);

	public Image update(Image image, Long id);

}
