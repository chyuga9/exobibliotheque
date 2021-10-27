package com.example.exobibliotheque;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;


@SpringBootApplication
@Log4j2
public class ExobibliothequeApplication {

	private static final Logger logger = LogManager.getLogger(ExobibliothequeApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ExobibliothequeApplication.class, args);
		logger.info("Démarrage de l'api");
	}

}
