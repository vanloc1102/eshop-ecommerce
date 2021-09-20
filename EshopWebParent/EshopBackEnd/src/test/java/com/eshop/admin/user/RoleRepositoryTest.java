package com.eshop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.eshop.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository repository;
	
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("admin", "manager everything");
		Role saveRole = repository.save(roleAdmin);
		assertThat(saveRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRole() {
		List<Role> listRole = new ArrayList<>();
		Role roleSalesperson = new Role("salesperson", "manager product price, customers, "
				+ "shipping, orders and sales report");
		Role roleEditor = new Role("editor", "manager categories, brands, products, "
				+ "articles and menus");
		Role roleShipper = new Role("shipper", "view products, view orders and update"
				+ "order status");
		Role roleAssistant = new Role("assitstant", "manager question and reviews");
		listRole.add(roleAssistant);
		listRole.add(roleShipper);
		listRole.add(roleEditor);
		listRole.add(roleSalesperson);
		repository.saveAll(listRole);
		
	}
}
