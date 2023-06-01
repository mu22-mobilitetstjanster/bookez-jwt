package com.example.jwtdemo.exception;

public class ExpiredTokenException extends JwtProcessException {
  public ExpiredTokenException(String message, int status) {
    super(message, status);
  }
}
