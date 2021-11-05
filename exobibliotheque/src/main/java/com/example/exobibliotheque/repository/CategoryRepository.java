package com.example.exobibliotheque.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.exobibliotheque.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String>{

}
