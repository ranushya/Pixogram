package com.social.pixogram.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.social.pixogram.model.User;


public interface UserService {

	public List<User> getUsers();
	
	public User createUser(User user);
	
	public Optional<User> getUserById(Long userId);

	public List<User> getUserByUserName(String name);
	

}
