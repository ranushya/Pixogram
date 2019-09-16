package com.social.pixogram.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.pixogram.model.User;
import com.social.pixogram.repo.UserRepo;
import com.social.pixogram.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepo repository;

	@GetMapping(path = "/users")
	public List<User> getUsers() {
		System.out.println("Retrieving the users");
		return userService.getUsers();
	}

	@PostMapping(path = "/user/create")
	public User createUser(@RequestBody User user) {
		System.out.println("Creating the User Account");
		User _user = userService.createUser(user);
		return _user;
	}
	
	@GetMapping(path = "/user/search/{name}")
	public List<User> getUserByUserName(@PathVariable String name) {
	System.out.println("Retreiving the user with  " + name);
		List<User> searches=userService.getUserByUserName(name);
		return searches;
	}

	@GetMapping(path = "/user/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		System.out.println("Retreiving the user with  " + id);
		return userService.getUserById(id);
	}
	
	/*@PutMapping("/user/update/{userId}")
	public Optional<User> updateUser(@PathVariable("userId") long userId,@RequestBody User user) {
		
		Optional<User> currentUser = null;
		if (currentUser.isPresent()) {
			User update_user= currentUser.get();
			update_user.setName(user.getName());
			update_user.setEmail(user.getEmail());
			update_user.setPassword(user.getPassword());
			return currentUser;
			//return new Optinal<User>(userService.save(update_user), HttpStatus.OK);
		} 
		return currentUser;
	}*/
	@PutMapping("/user/update/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") long userId, @RequestBody User user) {
		//System.out.println("Update Customer with ID = " + id + "...");

		Optional<User> currentUser = repository.findById(userId);

		if (currentUser.isPresent()) {
			User _user = currentUser.get();
			_user.setName(user.getName());
			_user.setEmail(user.getEmail());
			_user.setPassword(user.getPassword());
			System.out.println(_user.getName());
			System.out.println(_user.getEmail());
			return new ResponseEntity<>(repository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
