package com.bookstore.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.adminportal.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByName(String name);
}
