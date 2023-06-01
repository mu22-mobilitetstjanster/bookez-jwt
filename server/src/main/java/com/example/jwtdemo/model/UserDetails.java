package com.example.jwtdemo.model;

import com.example.jwtdemo.type.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetails {
  private String username;
  private Role role;
}
