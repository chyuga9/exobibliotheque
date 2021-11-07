package com.example.exobibliotheque.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.Category;
import com.example.exobibliotheque.model.Genre;
import com.example.exobibliotheque.model.ListOfOeuvres;
import com.example.exobibliotheque.model.ListOfOeuvresId;
import com.example.exobibliotheque.model.Oeuvre;
import com.example.exobibliotheque.repository.CategoryRepository;
import com.example.exobibliotheque.repository.GenreRepository;
import com.example.exobibliotheque.repository.ListOfOeuvresRepository;
import com.example.exobibliotheque.repository.OeuvreRepository;

@Service
public class GenreService {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);

	@Autowired
	private GenreRepository genreRepository;

	// ---------- Méthodes de base --------

	public Iterable<Genre> getGenres() {
		logger.info("Service - Recherche de tous les genres");
		return genreRepository.findAll();
	}
	
	public Optional<Iterable<Genre>> getGenres(String genre) {
		logger.info("Service - Recherche des genres correspondant à " + genre);
		return genreRepository.findByGenreContaining(genre);
	}
	
/*
	// Surement ajouter un try and catch mais avant vérifier ce qu'il se passe avec
	// un id qui n'existe pas
	public Oeuvre getSingleOeuvre(int oeuvreId) {
		if (!oeuvreRepository.existsById(oeuvreId))
			throw new NullPointerException("Aucune personne n'a été trouvée avec l'id " + oeuvreId);
		return oeuvreRepository.findById(oeuvreId).get();
	}

	public Oeuvre saveOeuvre(Oeuvre oeuvre) {
		logger.info("Enregistrement d'une nouvelle oeuvre");
		return oeuvreRepository.save(oeuvre);
	}

	public void deleteOeuvre(int oeuvreId) {
		oeuvreRepository.deleteById(oeuvreId);
	}

	public Oeuvre updateOeuvre(Oeuvre oeuvre) {
		return oeuvreRepository.save(oeuvre);
	}

	public boolean addOeuvre(int oeuvreId, ListOfOeuvresId listId) {
		Oeuvre oeuvre = oeuvreRepository.findById(oeuvreId).get();
		// logger.info(listId);
		// Je n'arrive pas à obtenir son id du coup je passe par la classe de l'id pour
		// obtenir la liste souhaitée
		ListOfOeuvres list = listRepository.findById(listId).get();
		if(!list.getOeuvres().contains(oeuvre)) {
		list.getOeuvres().add(oeuvre);
		listRepository.save(list);
		logger.info(oeuvre.getName() + " a été ajouté à la liste " + listId.getName());
		return true;
		}else {
			logger.info(oeuvre.getName() + " est déjà dans la liste " + listId.getName());
			return false;
		}
	}
*/
}
