package com.example.exobibliotheque.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.exobibliotheque.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
