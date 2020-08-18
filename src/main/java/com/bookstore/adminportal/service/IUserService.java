package com.bookstore.adminportal.service;


import java.util.Set;

import com.bookstore.adminportal.security.PasswordResetToken;
import com.bookstore.adminportal.security.UserRole;
import com.bookstore.adminportal.models.User;

public interface IUserService {
	PasswordResetToken getPasswordResetToken(final String otken);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
}
