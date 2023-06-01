package com.example.jwtdemo.repository;

import com.example.jwtdemo.model.UserDetails;
import com.example.jwtdemo.type.Role;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

  private List<UserDetails> users = new LinkedList<>() {{
    add(new UserDetails("Greta", Role.ADMIN));
    add(new UserDetails("Bob", Role.USER));
    add(new UserDetails("Yves", Role.USER));
  }};

  @Override
  public Optional<UserDetails> getUser(String username) {
    return users
            .stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst();
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
