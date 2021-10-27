package com.example.exobibliotheque.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.ListOfOeuvres;
import com.example.exobibliotheque.repository.ListOfOeuvresRepository;

@Service
public class ListOfOeuvresService {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);

	@Autowired
	private ListOfOeuvresRepository listOfOeuvresRepository;
	
	//---------- Méthodes de base --------

	public Iterable<ListOfOeuvres> getListsOfOeuvres(int userid){
		logger.info("lancement service");
		return listOfOeuvresRepository.findByUserId(userid);
	}
	
}
