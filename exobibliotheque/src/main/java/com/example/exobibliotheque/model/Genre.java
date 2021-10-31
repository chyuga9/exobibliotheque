package com.example.exobibliotheque.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
public class Genre {

	// A reflechir si je change par une énumeration mais pas sur que ca convienne
	@Id
	private String genre; 
}
