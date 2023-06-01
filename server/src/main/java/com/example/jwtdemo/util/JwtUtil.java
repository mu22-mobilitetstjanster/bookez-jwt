package com.example.jwtdemo.util;

import com.example.jwtdemo.model.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

  static final String SECRET_KEY = "catsareawesomebutdogsareawesometooandletsnotforgetabouttheparrotstheyareawesometoo";
  static final long EXPIRE_IN = 1000 * 60 * 60; // 1h

  public static String sign(UserDetails userDetails) {

    Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    return Jwts
            .builder()
            .claim("username", userDetails.getUsername())
            .claim("role", userDetails.getRole())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_IN))
            .signWith(key)
            .compact();
  }

  public static boolean verify(String token) {
    Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    return Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parse(token) != null;
  }
}
