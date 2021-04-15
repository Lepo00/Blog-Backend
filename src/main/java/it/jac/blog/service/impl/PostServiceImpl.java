package it.jac.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.blog.model.Post;
import it.jac.blog.repository.PostRepository;
import it.jac.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepository postRepository;

	@Override
	public Optional<Post> get(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public Post create(Post c) {
		return postRepository.save(c);
	}

	@Override
	public void createAll(List<Post> posts) {
		postRepository.saveAll(posts);
	}

	@Override
	public void delete(Long id) {
		postRepository.deleteById(id);
	}

	@Override
	public Post update(Post post, Long id) {
		return postRepository.findById(id).map(c -> { // update if entity already exists
			post.setId(c.getId());
			return create(post);
		}).orElseGet(() -> { // create if entity not exists
			return postRepository.save(post);
		});
	}
}
