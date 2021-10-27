package com.example.exobibliotheque.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.ListOfOeuvres;
import com.example.exobibliotheque.model.Oeuvre;
import com.example.exobibliotheque.service.ListOfOeuvresService;

@RestController
public class ListOfOeuvresController {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);
	
	@Autowired
	private ListOfOeuvresService listOfOeuvresService;
	
	@GetMapping
	public ResponseEntity<Iterable<ListOfOeuvres>> getListsOfOeuvres(int userid){
		logger.info("Recherche de toutes les listes de l'utilisateur");
		return ResponseEntity.ok().body(listOfOeuvresService.getListsOfOeuvres(userid));
	}
}
