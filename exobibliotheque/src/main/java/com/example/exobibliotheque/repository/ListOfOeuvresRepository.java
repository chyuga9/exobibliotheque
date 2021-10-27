package com.example.exobibliotheque.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.exobibliotheque.model.ListOfOeuvres;

@Repository
public interface ListOfOeuvresRepository extends CrudRepository<ListOfOeuvres, Integer> {

	Iterable<ListOfOeuvres> findByUserId(int userid);

	
}
