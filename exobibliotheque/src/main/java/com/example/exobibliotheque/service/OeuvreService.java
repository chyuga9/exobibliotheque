package com.example.exobibliotheque.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exobibliotheque.ExobibliothequeApplication;
import com.example.exobibliotheque.model.Oeuvre;
import com.example.exobibliotheque.repository.OeuvreRepository;


@Service
public class OeuvreService {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);

	@Autowired
	private OeuvreRepository oeuvreRepository;
	
	//---------- Méthodes de base --------

	public Iterable<Oeuvre> getOeuvres(){
		logger.info("lancement service");
		return oeuvreRepository.findAll();
	}
}
