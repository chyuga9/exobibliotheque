package com.example.exobibliotheque.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ListOfOeuvresId implements Serializable {

	private String userid;
	private String name;
}
