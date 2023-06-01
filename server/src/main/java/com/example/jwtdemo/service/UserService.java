package com.example.jwtdemo.service;

import com.example.jwtdemo.model.AuthDetails;
import com.example.jwtdemo.model.UserDetails;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
  UserDetails get(String username);
  UserDetails add(AuthDetails authDetails);
  List<UserDetails> getAll();
}
