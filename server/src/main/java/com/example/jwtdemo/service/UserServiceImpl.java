package com.example.jwtdemo.service;

import com.example.jwtdemo.model.AuthDetails;
import com.example.jwtdemo.model.UserDetails;
import com.example.jwtdemo.repository.UserRepository;
import com.example.jwtdemo.type.Role;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails get(String username) {
    return userRepository.getUser(username);
  }

  @Override
  public UserDetails add(AuthDetails authDetails) {
    var user = this.get(authDetails.getUsername());
    if(user != null) return null;

    user = new UserDetails(authDetails.getUsername(), Role.USER);
    userRepository.addUser(user);
    return user;
  }

  @Override
  public List<UserDetails> getAll() {
    return userRepository.getAll();
  }
}
