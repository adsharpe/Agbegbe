package com.ironrookcomputing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironrookcomputing.hibernate.beans.Login;
import com.ironrookcomputing.hibernate.beans.User;
import com.ironrookcomputing.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {
	@PutMapping
	public ResponseEntity<User> addUser(@RequestBody Login login) {
		User user = userService.addUser(login);
		
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(409).build();
	}
	
	@GetMapping
	public ResponseEntity<User> getUser(@RequestParam(value = "username") String username) {
		User user = userService.getUser(username);
		
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(404).build();
	}
	
	@PostMapping
	public ResponseEntity<User> updateUser(@RequestParam(value = "user") User user) {
		user = userService.updateUser(user);
		
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(404).build();
	}
	
	@DeleteMapping
	public ResponseEntity<User> deleteUser(@RequestParam(value = "user") User user) {
		userService.deleteUser(user);
		return ResponseEntity.status(404).build();
	}
	
	@Autowired
	private UserService userService;
}
