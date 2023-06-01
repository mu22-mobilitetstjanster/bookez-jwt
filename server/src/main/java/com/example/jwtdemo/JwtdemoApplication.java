package com.example.jwtdemo;

import com.example.jwtdemo.filter.AuthFilter;
import com.example.jwtdemo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;

@SpringBootApplication
public class JwtdemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(JwtdemoApplication.class, args);
  }

  @Bean
  public OncePerRequestFilter corsFilter() {
    return new OncePerRequestFilter() {
      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "authorization,content-type");

        if(request.getMethod().equals("OPTIONS")) {
          response.setStatus(HttpServletResponse.SC_OK);
        } else {
          filterChain.doFilter(request, response);
        }
      }
    };
  }

  @Bean
  public OncePerRequestFilter authFilter() {
    return new AuthFilter(new HashMap<>() {{
        put("/users/", new AuthFilter.GuardedPath(true, true));
        put("/books/", new AuthFilter.GuardedPath(true, false));
      }}
    );
  }
}
