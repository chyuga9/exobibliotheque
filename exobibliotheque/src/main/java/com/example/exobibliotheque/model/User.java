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
	
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
			)
	@JoinColumn
	private List<ListOfOeuvres> lists;
	
	private boolean isAdmin;
	
	private void addList(ListOfOeuvres list) {
		lists.add(list);
	}
}
