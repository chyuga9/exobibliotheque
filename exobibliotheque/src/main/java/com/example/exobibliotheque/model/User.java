package com.example.exobibliotheque.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Embeddable
@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String email;
	
	private String password;
	private boolean isAdmin;
	
	// demander si ca vaut le coup de garder cette liste et si on garde du coup le bout de code en commentaire dans listofoeuvresservice.createlist
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
			)
	@JoinColumn(name="user")
	private List<ListOfOeuvres> lists;
	
	private void addList(ListOfOeuvres list) {
		lists.add(list);
	}
}
