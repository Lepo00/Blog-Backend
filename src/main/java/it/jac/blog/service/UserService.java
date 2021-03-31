package it.jac.blog.service;

import java.util.List;
import java.util.Optional;

import it.jac.blog.model.User;

public interface UserService {
	public Optional<User> get(Long id);

	public User getByUsername(String ivaCode);

	public User create(User c);

	public void createAll(List<User> c);

	public void delete(Long id);

	public User update(User c, Long id);

}
