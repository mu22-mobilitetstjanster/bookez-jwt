package com.example.jwtdemo.exception;

public abstract class JwtProcessException extends RuntimeException {
  private int status;

  public JwtProcessException(String message, int status) {
    super(message);
    this.status = status;
  }

  public int getStatus() {
    return this.status;
  }
}
