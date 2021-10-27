package com.example.exobibliotheque.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String email;
	
	private String password;
	
	private List<ListOfOeuvres> lists;
	
	private boolean isAdmin;
	
	private void addList(ListOfOeuvres list) {
		lists.add(list);
	}
}
