package com.example.exobibliotheque.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.User;
import com.example.exobibliotheque.repository.UserRepository;

public class UserService {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public Iterable<User> getUsers() {
		logger.info("Service - Recherche tous les users");
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		logger.info("Service - Création d'un nouvel utilisateur");
		return userRepository.save(user);
	}

}
