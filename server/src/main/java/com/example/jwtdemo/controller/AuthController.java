package com.example.jwtdemo.controller;

import com.example.jwtdemo.exception.UserAlreadyExistException;
import com.example.jwtdemo.model.AuthDetails;
import com.example.jwtdemo.model.UserDetails;
import com.example.jwtdemo.service.UserService;
import com.example.jwtdemo.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/auth/*")
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("login")
  public ResponseEntity<String> signIn(
          @RequestBody AuthDetails authDetails) {

    try {
      UserDetails user = userService.get(authDetails.getUsername());
      return ResponseEntity.ok(JwtUtil.sign(user));
    } catch(NoSuchElementException ex) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  }

  @PostMapping("register")
  public ResponseEntity<String> signUp(
          @RequestBody AuthDetails authDetails) {

    try {
      UserDetails user = userService.add(authDetails);
      return ResponseEntity.ok(JwtUtil.sign(user));
    } catch(UserAlreadyExistException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @PostMapping("verify")
  public ResponseEntity<String> verifyToken(@RequestBody String token) {

    try {
      JwtUtil.verify(token);
      return ResponseEntity.ok("Successfully verified token");
    } catch(SignatureException ex) {
      return ResponseEntity.status(401).body("Token signature is invalid");
    } catch(ExpiredJwtException ex) {
      return ResponseEntity.status(400).body("Token expired, request a new token");
    } catch(MalformedJwtException ex) {
      return ResponseEntity.status(400).body("Token was malformed");
    }
  }
}
