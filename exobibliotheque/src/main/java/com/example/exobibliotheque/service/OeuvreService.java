package com.example.exobibliotheque.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.ListOfOeuvres;
import com.example.exobibliotheque.model.ListOfOeuvresId;
import com.example.exobibliotheque.model.Oeuvre;
import com.example.exobibliotheque.repository.ListOfOeuvresRepository;
import com.example.exobibliotheque.repository.OeuvreRepository;

@Service
public class OeuvreService {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);
	private final Path root = Paths.get("uploads");
	@Autowired
	private OeuvreRepository oeuvreRepository;

	@Autowired
	private ListOfOeuvresRepository listRepository;

	// ---------- Méthodes de base --------

	public Iterable<Oeuvre> getOeuvres() {
		logger.info("Recherche de toutes les oeuvres");
		return oeuvreRepository.findAll();
	}

	// Surement ajouter un try and catch mais avant vérifier ce qu'il se passe avec
	// un id qui n'existe pas
	public Oeuvre getSingleOeuvre(int oeuvreId) {
		if (!oeuvreRepository.existsById(oeuvreId))
			throw new NullPointerException("Aucune oeuvre n'a été trouvée avec l'id " + oeuvreId);
		return oeuvreRepository.findById(oeuvreId).get();
	}

	public Oeuvre saveOeuvre(Oeuvre oeuvre, MultipartFile file) {
		logger.info("Enregistrement d'une nouvelle oeuvre");
		
		  try {
			  if(!file.equals(null)){
				  oeuvre.setImage(this.root.resolve(file.getOriginalFilename()).toString());
				  Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			  }
			  return oeuvreRepository.save(oeuvre);
		    } catch (Exception e) {
		      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		    }
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

}
