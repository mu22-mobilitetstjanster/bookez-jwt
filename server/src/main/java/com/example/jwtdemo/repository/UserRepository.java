package com.example.jwtdemo.repository;

import com.example.jwtdemo.model.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
  Optional<UserDetails> getUser(String username);
  void addUser(UserDetails userDetails);
  List<UserDetails> getAll();
}
