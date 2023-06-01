package com.example.jwtdemo.service;

import com.example.jwtdemo.exception.UserAlreadyExistException;
import com.example.jwtdemo.model.AuthDetails;
import com.example.jwtdemo.model.UserDetails;
import com.example.jwtdemo.repository.UserRepository;
import com.example.jwtdemo.type.Role;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails get(String username) throws NoSuchElementException {
    return userRepository.getUser(username).get();
  }

  @Override
  public UserDetails add(AuthDetails authDetails) throws UserAlreadyExistException {
    var userOptional = userRepository.getUser(authDetails.getUsername());

    if(userOptional.isPresent()) {
      throw new UserAlreadyExistException("User with username " + authDetails.getUsername() + " already exist");
    }

    var user = new UserDetails(authDetails.getUsername(), Role.USER);
    userRepository.addUser(user);
    return user;
  }

  @Override
  public List<UserDetails> getAll() {
    return userRepository.getAll();
  }
}
