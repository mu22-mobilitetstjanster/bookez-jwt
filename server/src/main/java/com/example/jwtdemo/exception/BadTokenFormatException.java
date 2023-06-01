package com.example.jwtdemo.exception;

public class BadTokenFormatException extends JwtProcessException {
  public BadTokenFormatException(String message, int status) {
    super(message, status);
  }
}
