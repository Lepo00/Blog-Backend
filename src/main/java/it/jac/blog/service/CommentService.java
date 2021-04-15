package it.jac.blog.service;

import java.util.List;
import java.util.Optional;

import it.jac.blog.model.Comment;

public interface CommentService {
	public Optional<Comment> get(Long id);

	public Comment create(Comment comment);

	public void createAll(List<Comment> comments);

	public void delete(Long id);

	public Comment update(Comment comment, Long id);

}
