package com.example.exobibliotheque.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.exobibliotheque.model.Oeuvre;

@Repository
public interface OeuvreRepository extends CrudRepository<Oeuvre, Integer>{

}
