package com.example.jwtdemo.exception;

public class UnauthorizedException extends JwtProcessException {
  public UnauthorizedException(String message, int status) {
    super(message, status);
  }
}
