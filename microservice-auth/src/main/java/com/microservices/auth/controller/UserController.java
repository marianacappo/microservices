package com.microservices.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.auth.domain.User;
import com.microservices.auth.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value="/user")
	public List<User> listUsers() {
		return userService.findAll();
	}
	
	@PostMapping(value="/user")
	public User create(@RequestBody User user) {
		return userService.save(user);
	}
	
	@DeleteMapping(value="/user/{id}")
	public String delete(@PathVariable(value = "id") Long id) {
		userService.delete(id);
		return "success";
	}
}
