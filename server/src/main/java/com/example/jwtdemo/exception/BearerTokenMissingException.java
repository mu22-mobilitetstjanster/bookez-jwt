package com.example.jwtdemo.exception;

public class BearerTokenMissingException extends JwtProcessException{
  public BearerTokenMissingException(String message, int status) {
    super(message, status);
  }
}
