package com.eshop.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eshop.common.entity.Role;
import com.eshop.common.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> listAll() {
		return (List<User>) repository.findAll();
	}

	public List<Role> listAllRoles() {
		return (List<Role>) roleRepository.findAll();
	}

	public void save(User user) {
		boolean isUpdate = (user.getId() != null);
		if (isUpdate) {
			User existingUser = repository.findById(user.getId()).get();
			
			if (user.getPassword().isEmpty()) {
				user.setPassword(existingUser.getPassword());
			} else {
				encodePassword(user);
			}
		} else {
			encodePassword(user);
		}
		
		repository.save(user);
	}

	private void encodePassword(User user) {
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
	}

	public boolean isEmailUnique(Integer id, String email) {
		User user = repository.getUserByEmail(email);
		
		if (user == null) return true;
		
		boolean isCreatingNew = (id == null);
		
		if (isCreatingNew) {
			if (user != null) return false;
		} else {
			if (user.getId() != id) {
				return false;
			}
		}
		
		return true;
	}

	public User getUserById(Integer id) throws UserNotFoundException {
		try {
			return repository.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new UserNotFoundException("Could not find any user with ID: " + id);
		}
	}
}
