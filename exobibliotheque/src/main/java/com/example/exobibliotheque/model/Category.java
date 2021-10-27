package com.example.exobibliotheque.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Category {

	// A reflechir si je change par une énumeration mais pas sur que ca convienne
	@Id
	private String category; 
}
