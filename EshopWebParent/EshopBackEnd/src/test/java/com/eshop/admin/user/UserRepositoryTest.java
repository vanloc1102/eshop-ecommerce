package com.eshop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.eshop.common.entity.Role;
import com.eshop.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateUserWithOneRoll() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userLocNV = new User("nguyenvanlocmmo@gmail.com", "123456", "Loc", "Nguyen Van");
		userLocNV.addRole(roleAdmin);

		User userSaved = repository.save(userLocNV);
		assertThat(userSaved.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateUserWithTwoRoll() {
		User userTieuViem = new User("tieuviem@gmail.com", "123456", "Viem", "Tieu");
		Role roleEditor = new Role(4);
		Role roleAssitstand = new Role(2);

		userTieuViem.addRole(roleEditor);
		userTieuViem.addRole(roleAssitstand);

		User userSaved = repository.save(userTieuViem);
		assertThat(userSaved.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repository.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {
		User user = repository.findById(1).get();
		System.out.println(user);

		assertThat(user).isNotNull();
	}

	@Test
	public void testUpdateUser() {
		User user = repository.findById(1).get();
		user.setEnable(true);
		user.setPassword("654321");

		repository.save(user);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User user = repository.findById(2).get();
		Role roleEditor = new Role(4);
		Role roleSalesPersonRole = new Role(5);
		user.getRoles().remove(roleEditor);
		user.addRole(roleSalesPersonRole);
		
		repository.save(user);
	}
	
	@Test
	public void testDeleteUser() {
		Integer id = 2;
		repository.deleteById(id);
	}
}
