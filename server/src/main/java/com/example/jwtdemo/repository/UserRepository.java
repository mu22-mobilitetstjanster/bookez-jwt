package com.example.jwtdemo.repository;

import com.example.jwtdemo.model.UserDetails;

import java.util.List;

public interface UserRepository {
  UserDetails       getUser(String username);
  void              addUser(UserDetails userDetails);
  List<UserDetails> getAll();
}
