package com.example.exobibliotheque.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.User;
import com.example.exobibliotheque.service.UserService;

@RestController
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<Iterable<User>> getUsers(){
		logger.info("Controller - Recherche tous les users");
		return ResponseEntity.ok().body(userService.getUsers());
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user){
		logger.info("Controller - Création d'un nouveau user");
		
		User newUser = userService.createUser(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newUser.getId())
				.toUri();
	
		return ResponseEntity.created(location).body(newUser);

	}
}
