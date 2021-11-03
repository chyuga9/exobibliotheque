package com.example.exobibliotheque.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.ListOfOeuvres;
import com.example.exobibliotheque.model.ListOfOeuvresId;
import com.example.exobibliotheque.model.Oeuvre;
import com.example.exobibliotheque.model.User;
import com.example.exobibliotheque.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<Iterable<User>> getUsers(){
		logger.info("Controller - Recherche tous les users");
		return ResponseEntity.ok().body(userService.getUsers());
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getSingleUser(@PathVariable int userId){
		logger.info("Controller - Recherche de l'utilisateur "+ userId);
		return ResponseEntity.ok().body(userService.getSingleUser(userId));
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
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateOeuvre(@RequestBody User user){
		logger.info("Controller - Mise à jour d'une utilisateur");
		User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok().body(updatedUser);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		logger.info("Controller - Recherche de l'utilisateur avec l'id \""+ userId + "\" pour suppression");
		userService.deleteUser(userId);
		return ResponseEntity.ok().body("User deleted");
	}
}
