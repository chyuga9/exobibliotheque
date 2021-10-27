package com.example.exobibliotheque.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Comment {

	private String comment;
	
	private User user;
}
