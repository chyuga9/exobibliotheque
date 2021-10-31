package com.example.exobibliotheque.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;

import lombok.Data;

@Entity
@Data
public class ListOfOeuvres {
	
	@EmbeddedId
	private ListOfOeuvresId id;
			
	@ManyToMany(
			fetch = FetchType.EAGER,
				cascade = { 
						CascadeType.PERSIST, 
						CascadeType.MERGE 
						}	
				)
	@JoinTable(
			name = "listofoeuvres_oeuvre",
			joinColumns = {@JoinColumn(name = "listofoeuvres_id_userid"), @JoinColumn(name = "listofoeuvres_id_name")	},
			inverseJoinColumns = @JoinColumn(name = "oeuvre_id_")
	)
	private List<Oeuvre> oeuvres;
	
	
	
	private void addOeuvre(Oeuvre oeuvre) {
		oeuvres.add(oeuvre);
	}
	
	private void removeOeuvre(Oeuvre oeuvre) {
		oeuvres.remove(oeuvre);
	}

	

}
