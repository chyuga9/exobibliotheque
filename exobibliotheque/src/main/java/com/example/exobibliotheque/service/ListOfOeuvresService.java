package com.example.exobibliotheque.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.ListOfOeuvres;
import com.example.exobibliotheque.model.ListOfOeuvresId;
import com.example.exobibliotheque.repository.ListOfOeuvresRepository;
import com.example.exobibliotheque.repository.UserRepository;

@Service
public class ListOfOeuvresService {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);

	@Autowired
	private ListOfOeuvresRepository listOfOeuvresRepository;
	@Autowired
	private UserRepository userRepository;
	// ---------- Méthodes de base --------

	public Iterable<ListOfOeuvres> getListsOfOeuvres() {
		logger.info("Service - Recherche de toutes les listes");
		return listOfOeuvresRepository.findAll();
	}

	public ListOfOeuvres getSingleListOfOeuvres(String listName) {
		if(!listOfOeuvresRepository.existByIdName(listName))
			logger.info("Service - La liste \"" + listName + "\" n'existe pas");
		return listOfOeuvresRepository.findByIdName(listName).get();
	}

	public ListOfOeuvres createListsOfOeuvres(int userId, ListOfOeuvresId listId) {

		listId.setUserId(String.valueOf(userId));
		ListOfOeuvres newList = new ListOfOeuvres(listId);
		// demander si ça vaut le coup d'essayer de placer cette ligne et de conserver
		// la liste "ListOfOeuvres" de User
		// userRepository.findById(userId).get().getLists().add(newList);
		return listOfOeuvresRepository.save(newList);
	}

	public void deleteListOfOeuvres(String listName) {
		listOfOeuvresRepository.deleteByIdName(listName);
		
	}

}
