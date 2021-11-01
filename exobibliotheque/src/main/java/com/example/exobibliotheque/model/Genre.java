package com.example.exobibliotheque.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
public class Genre {

	// A reflechir si je change par une énumeration mais pas sur que ca convienne
	@Id
	private String genre; 
	
	/*
	 * Erreur qui me demandait de créer un constructeur pour Category...je ne sais pas pourquoi alors qu'il y l'annotation @Data
	 * 
	 */
		public Genre(String genre) {
			super();
			this.genre = genre;
		}

		/*
		 * Erreur qui me reclame un constructeur par défaut pour Category...pas d'explications
		 * org.hibernate.InstantiationException: No default constructor for entity:  : com.example.exobibliotheque.model.Category
		 * [2m2021-10-31 21:21:36.593[0;39m [31mERROR[0;39m [35m21004[0;39m [2m---[0;39m [2m[nio-9007-exec-3][0;39m [36mo.a.c.c.C.[.[.[/].[dispatcherServlet]   [0;39m [2m:[0;39m Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.orm.jpa.JpaSystemException: No default constructor for entity:  : com.example.exobibliotheque.model.Category; nested exception is org.hibernate.InstantiationException: No default constructor for entity:  : com.example.exobibliotheque.model.Category] with root cause
		 */
		public Genre() {
			super();
			// TODO Auto-generated constructor stub
		}
}
