package com.example.jwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books/*")
public class BookController {

  @GetMapping
  public List<String> allBooks() {
    return List.of("Lord of the Rings", "Nightingale", "Head First Java", "100 åringen som klev ut ur ett fönster");
  }
}
