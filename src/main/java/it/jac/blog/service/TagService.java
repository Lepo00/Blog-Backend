package it.jac.blog.service;

import java.util.List;
import java.util.Optional;

import it.jac.blog.model.Tag;

public interface TagService {
	public Optional<Tag> get(Long id);

	public Tag create(Tag tag);

	public void createAll(List<Tag> tags);

	public void delete(Long id);

	public Tag update(Tag tag, Long id);

}
