package it.jac.blog.service;

import java.util.List;
import java.util.Optional;

import it.jac.blog.model.User;

public interface UserService {
	public Optional<User> get(Long id);

	public User getByUsername(String username);

	public User create(User user);

	public void createAll(List<User> users);

	public void delete(Long id);

	public User update(User user, Long id);

}
