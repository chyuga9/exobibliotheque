package com.example.exobibliotheque.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ListOfOeuvresController {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);
	
	@Autowired
	private ListOfOeuvresService listOfOeuvresService;
	
	// ----- Controllers de base
	
	@GetMapping("/listsofoeuvres")
	public ResponseEntity<Iterable<ListOfOeuvres>> getListsOfOeuvres(){
		logger.info("Controller - Recherche de toutes les listes de l'utilisateur");
		return ResponseEntity.ok().body(listOfOeuvresService.getListsOfOeuvres());
	}
	
	@GetMapping("/listsofoeuvres/{listName}")
	public ResponseEntity<ListOfOeuvres> getSingleListOfOeuvres(@PathVariable String listName){
		logger.info("Controller - Recherche de la liste utilisateur \"" + listName + "\"");
		return ResponseEntity.ok().body(listOfOeuvresService.getSingleListOfOeuvres(listName));
	}
	
	@PostMapping("/listofoeuvres")
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
	
	@DeleteMapping("/listofoeuvres/{listName}")
	public ResponseEntity<String> deleteListOfOeuvres(@PathVariable String listName){
		logger.info("Controller - Recherche de la liste \""+ listName + "\" pour suppression");
		listOfOeuvresService.deleteListOfOeuvres(listName);
		return ResponseEntity.ok().body("List deleted");
	}
	
	// ----- Controllers supplémetaires
	
	@DeleteMapping("/listofoeuvres/deleteoeuvrefromlist")
	public ResponseEntity<Boolean> deleteOeuvreFromList( @RequestBody Oeuvre oeuvre, @RequestParam ListOfOeuvresId listId) {
		logger.info("Controller - Recherche de l'oeuvre avec l'id " + oeuvre.getId() + "pour suppression de la liste");
		listOfOeuvresService.deleteOeuvreFromList(oeuvre, listId);
		return ResponseEntity.ok().build();
	}
}
