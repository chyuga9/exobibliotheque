package com.example.exobibliotheque.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Note {

	private int note;
	
	private User user;
}
