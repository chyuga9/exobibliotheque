package com.example.exobibliotheque.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.exobibliotheque.service.OeuvreService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class OeuvreController {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);

	@Autowired
	private OeuvreService oeuvreService;

	// -------- Controllers BDD Oeuvres

	@GetMapping("/oeuvres")
	public ResponseEntity<Iterable<Oeuvre>> getOeuvres() {
		logger.info("Controller - Recherche de toutes les oeuvres");
		return ResponseEntity.ok().body(oeuvreService.getOeuvres());
	}

	@GetMapping("/oeuvres/{id}")
	public ResponseEntity<Oeuvre> getSingleOeuvre(@PathVariable int oeuvreId) {
		logger.info("Controller - Recherche de l'oeuvre avec l'id " + oeuvreId);
		return ResponseEntity.ok().body(oeuvreService.getSingleOeuvre(oeuvreId));
	}

	@PostMapping("/oeuvre")
	public ResponseEntity<Oeuvre> createOeuvre(@RequestBody Oeuvre oeuvre) {
		logger.info("Controller - Ajout d'une oeuvre");

		Oeuvre newOeuvre = oeuvreService.saveOeuvre(oeuvre);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(oeuvre.getId())
				.toUri();

		return ResponseEntity.created(location).body(newOeuvre);
	}

	@PutMapping("/oeuvre/{id}")
	public ResponseEntity<Oeuvre> updateOeuvre(@RequestBody Oeuvre oeuvre) {
		logger.info("Controller - Mise à jour d'une oeuvre");
		Oeuvre updatedOeuvre = oeuvreService.updateOeuvre(oeuvre);
		return ResponseEntity.ok().body(updatedOeuvre);
	}

	@DeleteMapping("/oeuvre/{id}")
	public ResponseEntity<String> deleteOeuvre(@PathVariable int oeuvreId) {
		logger.info("Controller - Recherche de l'oeuvre avec l'id " + oeuvreId + "pour suppression");
		oeuvreService.deleteOeuvre(oeuvreId);
		return ResponseEntity.ok().body("Oeuvre deleted");
	}

	// -------- Controllers autre

	// Est ce que je peux vraiment envoyer une réponse vide ?
	@PostMapping("/oeuvre/addoeuvreinlist")
	public ResponseEntity<String> addOeuvre(@RequestParam int oeuvreId, @RequestBody ListOfOeuvresId listId) {
		logger.info("Controller - Ajout d'une oeuvre dans une liste");
		if (oeuvreService.addOeuvre(oeuvreId, listId)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}
}
