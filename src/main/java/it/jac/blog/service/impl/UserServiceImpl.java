package it.jac.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.blog.model.User;
import it.jac.blog.repository.UserRepository;
import it.jac.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Optional<User> get(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User create(User c) {
		return userRepository.save(c);
	}

	@Override
	public void createAll(List<User> users) {
		userRepository.saveAll(users);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User update(User user, Long id) {
		return userRepository.findById(id).map(c -> { // update if entity already exists
			user.setId(c.getId());
			return create(user);
		}).orElseGet(() -> { // create if entity not exists
			return userRepository.save(user);
		});
	}
}
