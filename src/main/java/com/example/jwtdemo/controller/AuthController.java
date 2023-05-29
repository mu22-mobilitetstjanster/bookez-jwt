package com.example.jwtdemo.controller;

import com.example.jwtdemo.model.AuthDetails;
import com.example.jwtdemo.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/*")
public class AuthController {

  @PostMapping("login")
  public ResponseEntity<String> signIn(
          @RequestBody AuthDetails authDetails) {
    String token = JwtUtil.sign(authDetails.getUsername());

    return ResponseEntity.ok(token);
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
