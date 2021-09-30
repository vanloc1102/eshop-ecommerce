package com.eshop.admin.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eshop.common.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	//Using Named Parameters
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	//Query Creation
	public User findByEmail(String email);
	
	
}
