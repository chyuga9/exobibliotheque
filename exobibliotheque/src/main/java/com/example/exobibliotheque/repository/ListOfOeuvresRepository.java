package com.example.exobibliotheque.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.exobibliotheque.model.ListOfOeuvres;
import com.example.exobibliotheque.model.ListOfOeuvresId;

@Repository
public interface ListOfOeuvresRepository extends CrudRepository<ListOfOeuvres, ListOfOeuvresId> {

	Iterable<ListOfOeuvres> findByIdUserId(String userid);
	Optional<ListOfOeuvres> findByIdName(String name);
	boolean existsByIdName(String listName);
	void deleteByIdName(String listName);


	
}
