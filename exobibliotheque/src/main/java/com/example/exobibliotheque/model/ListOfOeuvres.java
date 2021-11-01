package com.example.exobibliotheque.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListOfOeuvres {
	
	@EmbeddedId
	private ListOfOeuvresId id;
			
	@ManyToMany(
			fetch = FetchType.EAGER
				)
	@JoinTable(
			name = "listofoeuvres_oeuvre",
			joinColumns = {@JoinColumn(name = "listofoeuvres_id_userid"), @JoinColumn(name = "listofoeuvres_id_name")	},
			inverseJoinColumns = @JoinColumn(name = "oeuvre_id_")
	)
	private List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
	
	
	
	public void addOeuvre(Oeuvre oeuvre) {
		oeuvres.add(oeuvre);
	}
	
	private void removeOeuvre(Oeuvre oeuvre) {
		oeuvres.remove(oeuvre);
	}

	public ListOfOeuvres(ListOfOeuvresId id) {
		super();
		this.id = id;
	}

	

}
