package com.eshop.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.common.entity.Role;
import com.eshop.common.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> listAll() {
		return (List<User>) repository.findAll();
	}
	
	public List<Role> listAllRoles() {
		return (List<Role>) roleRepository.findAll();
	}

	public void save(User user) {
		repository.save(user);
	}
}
