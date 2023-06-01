package com.example.jwtdemo.type;

public enum Role {
  GUEST("GUEST"), USER("USER"), ADMIN("ADMIN");

  final String value;
  Role(String value) { this.value = value; }
}
