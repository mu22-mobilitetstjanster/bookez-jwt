package com.example.jwtdemo.controller;

import com.example.jwtdemo.model.UserDetails;
import com.example.jwtdemo.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/*")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDetails>> getAllUserDetails() {
    return ResponseEntity.ok(userService.getAll());
  }
}
