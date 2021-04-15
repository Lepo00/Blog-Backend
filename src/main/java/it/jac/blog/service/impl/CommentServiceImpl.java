package it.jac.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.blog.model.Comment;
import it.jac.blog.repository.CommentRepository;
import it.jac.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public Optional<Comment> get(Long id) {
		return commentRepository.findById(id);
	}

	@Override
	public Comment create(Comment c) {
		return commentRepository.save(c);
	}

	@Override
	public void createAll(List<Comment> comments) {
		commentRepository.saveAll(comments);
	}

	@Override
	public void delete(Long id) {
		commentRepository.deleteById(id);
	}

	@Override
	public Comment update(Comment comment, Long id) {
		return commentRepository.findById(id).map(c -> { // update if entity already exists
			comment.setId(c.getId());
			return create(comment);
		}).orElseGet(() -> { // create if entity not exists
			return commentRepository.save(comment);
		});
	}
}
