package com.example.exobibliotheque.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Note {

	@EmbeddedId
	private NoteId id;
	
	private byte note;
}
