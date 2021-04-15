package it.jac.blog.service;

import java.util.List;
import java.util.Optional;

import it.jac.blog.model.Post;

public interface PostService {
	public Optional<Post> get(Long id);

	public Post create(Post post);

	public void createAll(List<Post> posts);

	public void delete(Long id);

	public Post update(Post post, Long id);

}
