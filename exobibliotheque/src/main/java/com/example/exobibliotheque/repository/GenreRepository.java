package com.example.exobibliotheque.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.exobibliotheque.model.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, String>{

}
