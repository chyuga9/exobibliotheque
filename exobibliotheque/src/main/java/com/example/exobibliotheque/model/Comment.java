package com.example.exobibliotheque.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Comment {

	@EmbeddedId
	private CommentId id;
	
	private String comment;

}
