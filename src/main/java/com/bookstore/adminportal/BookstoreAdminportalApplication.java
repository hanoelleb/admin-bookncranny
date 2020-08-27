package com.bookstore.adminportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.adminportal.models.User;
import com.bookstore.adminportal.security.Role;
import com.bookstore.adminportal.security.UserRole;
import com.bookstore.adminportal.service.impl.UserService;
import com.bookstore.adminportal.utility.SecurityUtility;

@SpringBootApplication
public class BookstoreAdminportalApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreAdminportalApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User demo = new User();
		demo.setUsername("admin");
		demo.setFirstName("Example");
		demo.setLastName("Admin");
		demo.setEmail("test@test.com");
		demo.setPassword(SecurityUtility.passwordEncoder().encode("test"));
		
		Set<UserRole> userRoles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(0);
		role.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(demo, role));
		
		userService.createUser(demo,  userRoles);	
	}
	
}
