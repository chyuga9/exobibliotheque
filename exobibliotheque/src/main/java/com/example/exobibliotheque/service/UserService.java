package com.example.exobibliotheque.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.Oeuvre;
import com.example.exobibliotheque.model.User;
import com.example.exobibliotheque.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);

	@Autowired
	private UserRepository userRepository;

	public Iterable<User> getUsers() {
		logger.info("Service - Recherche tous les users");
		return userRepository.findAll();
	}

	public User getSingleUser(int userId) {
		if (!userRepository.existsById(userId))
			logger.info("Service - L'utilisateur avec l'id \"" + userId + "\" n'existe pas");
		return userRepository.findById(userId).get();
	}

	public User createUser(User user) {
		logger.info("Service - Création d'un nouvel utilisateur");
		return userRepository.save(user);
	}

	public void deleteUser(int userId) {
		userRepository.deleteById(userId);

	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

}
