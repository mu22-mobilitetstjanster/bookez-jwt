package com.example.jwtdemo.repository;

import com.example.jwtdemo.model.UserDetails;
import com.example.jwtdemo.type.Role;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

  private List<UserDetails> users = new LinkedList<>() {{
    add(new UserDetails("Greta", Role.ADMIN));
    add(new UserDetails("Bob", Role.USER));
    add(new UserDetails("Yves", Role.USER));
  }};

  @Override
  public UserDetails getUser(String username) {
    return users
            .stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst()
            .get();
  }

  @Override
  public void addUser(UserDetails userDetails) {
    users.add(userDetails);
  }

  @Override
  public List<UserDetails> getAll() {
    return users;
  }
}
