package com.example.exobibliotheque.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ListOfOeuvres {
	
	@Id
	private int id;
	
	private String name;
	
	private User user;
	
	private List<Oeuvre> oeuvres;
	
	
	
	private void addOeuvre(Oeuvre oeuvre) {
		oeuvres.add(oeuvre);
	}
	
	private void removeOeuvre(Oeuvre oeuvre) {
		oeuvres.remove(oeuvre);
	}

	public ListOfOeuvres(String name) {
		this.name = name;
	}

}
