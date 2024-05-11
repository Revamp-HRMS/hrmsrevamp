package com.hrmsrevamp.service;

import com.hrmsrevamp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * JwtService interface.
 */
public interface JwtService {
  String extractUserEmail(String token);

  String generateTokenForAdmin(User user);

  String generateTokenForUser(User user);

  String generateRefreshToken(User user);

  String extractUserId(String token);


  boolean isTokenValid(String token, UserDetails userDetails);

  boolean isRefreshTokenValid(String token);
}
