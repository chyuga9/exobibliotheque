package com.example.exobibliotheque.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Embeddable;

@Embeddable
public class NoteId implements Serializable{

	private Date date;
	private int userid;
	private int oeuvreid;
	
}
