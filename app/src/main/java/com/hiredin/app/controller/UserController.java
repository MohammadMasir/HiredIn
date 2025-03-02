package com.hiredin.app.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiredin.app.model.User;
import com.hiredin.app.repository.UserServiceInterface;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name="User Management", description="Endpoints for managing users")
public class UserController {
	
	private final UserServiceInterface userService ;
	
	
	public UserController(UserServiceInterface userService) {
		this.userService = userService;
		
	}
	
	@GetMapping
	public ResponseEntity<?> allUser(){
		List<User> users = userService.getAllUsers();
		try {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> newUser(@RequestBody User user){
		if(userService.createUser(user)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable String id) {
		try {
			return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/by/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable String email) {
		try {
			return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id,@RequestBody User user){
		if (userService.updateUserEFL(id, user)) {
			User updatedUSer = userService.getUserById(id);
			return new ResponseEntity<>(updatedUSer,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id){
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
