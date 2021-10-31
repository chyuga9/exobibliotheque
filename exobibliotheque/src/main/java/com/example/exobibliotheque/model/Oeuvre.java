package com.example.exobibliotheque.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

import com.example.exobibliotheque.model.Category;


// On ne peut pas faire plusieurs Fetch.EAGER, donc j'essaye avec 1 eager et 2 lazy)
@Entity
@Data
public class Oeuvre {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
	private String name;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "category_name")
	private Category category;
	
	@ManyToMany(
			fetch = FetchType.EAGER,
				cascade = { 
						CascadeType.PERSIST, 
						CascadeType.MERGE 
						}	
				)
	@JoinTable(
			name = "oeuvre_genre",
			joinColumns = @JoinColumn(name = "oeuvre_id"),
			inverseJoinColumns = @JoinColumn(name = "genre_name")
	)
	private List<Genre> genres;
	
	private Date dateDeSortie;
		
	private String synopsis;
	
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
			)
	@JoinColumn
	private List<Note> notes;
	
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
			)
	@JoinColumn
	private List<Comment> comments;
	
	
	
	
}
