package com.example.exobibliotheque.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.exobibliotheque.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
