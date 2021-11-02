package com.example.exobibliotheque.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.ListOfOeuvres;
import com.example.exobibliotheque.model.ListOfOeuvresId;
import com.example.exobibliotheque.model.Oeuvre;
import com.example.exobibliotheque.service.ListOfOeuvresService;

@RestController
@RequestMapping("/api")
public class ListOfOeuvresController {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);
	
	@Autowired
	private ListOfOeuvresService listOfOeuvresService;
	
	@GetMapping("/listsofoeuvres")
	public ResponseEntity<Iterable<ListOfOeuvres>> getListsOfOeuvres(){
		logger.info("Controller - Recherche de toutes les listes de l'utilisateur");
		return ResponseEntity.ok().body(listOfOeuvresService.getListsOfOeuvres());
	}
	
	@PostMapping("/listofoeuvre")
	public ResponseEntity<ListOfOeuvres> createListsOfOeuvres(@RequestParam int userId, @RequestBody ListOfOeuvresId listId){
		logger.info("Controller - Création d'une liste d'utilisateur");
		
		ListOfOeuvres newList = listOfOeuvresService.createListsOfOeuvres(userId, listId);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newList.getId())
				.toUri();
		
		return ResponseEntity.created(location).body(newList);
	}
}
