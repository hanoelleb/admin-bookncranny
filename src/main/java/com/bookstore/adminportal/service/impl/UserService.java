package com.bookstore.adminportal.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.adminportal.models.User;
import com.bookstore.adminportal.repository.PasswordTokenResetRepository;
import com.bookstore.adminportal.repository.RoleRepository;
import com.bookstore.adminportal.repository.UserRepository;
import com.bookstore.adminportal.security.PasswordResetToken;
import com.bookstore.adminportal.security.UserRole;
import com.bookstore.adminportal.service.IUserService;

@Service
public class UserService implements IUserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordTokenResetRepository passwordTokenResetRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		return passwordTokenResetRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordTokenResetRepository.save(myToken);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if (localUser != null) {
			LOG.info("User already exists. Will not be added");
		} else {
			for (UserRole ur : userRoles ) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			localUser = userRepository.save(user);
		}
		
		return localUser;
	}
}
