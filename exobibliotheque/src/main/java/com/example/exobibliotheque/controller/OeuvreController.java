package com.example.exobibliotheque.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.example.exobibliotheque.service.OeuvreService;

@RestController
@RequestMapping("/api")
public class OeuvreController {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);
	
	@Autowired
	private OeuvreService oeuvreService;
	
	@GetMapping("/oeuvres")
	public ResponseEntity<Iterable<Oeuvre>> getOeuvres(){
		logger.info("Controller - Recherche de toutes les oeuvres");
		return ResponseEntity.ok().body(oeuvreService.getOeuvres());
	}
	
	@PostMapping("/oeuvre")
	public ResponseEntity<Oeuvre> createOeuvre(@RequestBody Oeuvre oeuvre){
		logger.info("Controller - Ajout d'une oeuvre");

		Oeuvre newOeuvre = oeuvreService.saveOeuvre(oeuvre);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(oeuvre.getId())
				.toUri();
		
		return ResponseEntity.created(location)
				.body(newOeuvre);
	}
	
	@PostMapping("/oeuvre/addoeuvreinlist")
	public ResponseEntity<Void> addOeuvre(@RequestParam int oeuvreId, @RequestBody ListOfOeuvresId listId){
		logger.info("Controller - Ajout d'une oeuvre dans une liste");
		oeuvreService.addOeuvre(oeuvreId,listId);
		return null;


	}
}
