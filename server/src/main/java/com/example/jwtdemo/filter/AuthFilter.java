package com.example.jwtdemo.filter;

import com.example.jwtdemo.exception.*;
import com.example.jwtdemo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AuthFilter extends OncePerRequestFilter {

  public static class GuardedPath {
    private final boolean isJwtGuarded, isAdminGuarded;
    public GuardedPath(boolean isJwtGuarded, boolean isAdminGuarded) {
      this.isJwtGuarded = isJwtGuarded;
      this.isAdminGuarded = isAdminGuarded;
    }
  }

  private final Map<String, GuardedPath> guardedPaths;

  public AuthFilter(Map<String, GuardedPath> guardedPaths) {
    this.guardedPaths = guardedPaths;
  }

  private String verifyAuthorizationPresence(HttpServletRequest request) {
    String authHeader = request.getHeader("authorization");

    if(authHeader == null) {
      throw new BearerTokenMissingException("Authorization header is missing", HttpServletResponse.SC_BAD_REQUEST);
    }
    else {
      return authHeader.replace("Bearer ", "");
    }
  }

  private Claims validateBearerToken(String token) {
    try {
      return JwtUtil.verify(token);
    }
   catch(SignatureException ex) {
      throw new UnauthorizedException("Token either missing access or is badly signed", HttpServletResponse.SC_UNAUTHORIZED);
    } catch(ExpiredJwtException ex) {
      throw new ExpiredTokenException("Token has expired, request a new", HttpServletResponse.SC_BAD_REQUEST);
    } catch(MalformedJwtException ex) {
      throw new BadTokenFormatException("Token has a bad format", HttpServletResponse.SC_BAD_REQUEST);
    }
  }

  private void processJwt(HttpServletRequest request, HttpServletResponse response, FilterChain chain, GuardedPath guardedPath) throws ServletException, IOException {
    try {
      var token = verifyAuthorizationPresence(request);
      var claims = validateBearerToken(token);

      if(guardedPath.isAdminGuarded && !claims.get("role").equals("ADMIN")) {
        throw new UnauthorizedException("User token does not have admin access", HttpServletResponse.SC_UNAUTHORIZED);
      }

      chain.doFilter(request, response);
    } catch(JwtProcessException ex) {
      ex.printStackTrace();
      response.sendError(ex.getStatus());
    }
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var requestPath = request.getServletPath();
    var guardedPath = guardedPaths.get(requestPath);

    if(guardedPath == null) {
      filterChain.doFilter(request, response);
    } else {
      processJwt(request, response, filterChain, guardedPath);
    }
  }
}
