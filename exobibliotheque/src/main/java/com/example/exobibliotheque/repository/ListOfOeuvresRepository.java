package com.example.exobibliotheque.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.exobibliotheque.model.ListOfOeuvres;
import com.example.exobibliotheque.model.ListOfOeuvresId;

@Repository
public interface ListOfOeuvresRepository extends CrudRepository<ListOfOeuvres, ListOfOeuvresId> {

	Iterable<ListOfOeuvres> findByIdUserid(String userid);
	Iterable<ListOfOeuvres> findByIdName(String name);


	
}
