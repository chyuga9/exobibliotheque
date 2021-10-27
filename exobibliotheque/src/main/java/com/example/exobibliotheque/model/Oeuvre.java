package com.example.exobibliotheque.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Oeuvre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	private Category categories;
	
	private Genre genres;
	
	private Date dateDeSortie;
	
	private String isbn;
	
	private String synopsis;
	
	private Note notes;
	
	private Comment comments;
	
	
	
	
}
