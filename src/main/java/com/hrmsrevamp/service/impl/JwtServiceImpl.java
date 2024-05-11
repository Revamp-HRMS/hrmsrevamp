package com.hrmsrevamp.service.impl;

import com.hrmsrevamp.constant.Constant;
import com.hrmsrevamp.constant.MessageEnum;
import com.hrmsrevamp.entity.Role;
import com.hrmsrevamp.entity.User;
import com.hrmsrevamp.exception.InvalidRequestException;
import com.hrmsrevamp.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * JwtService Implementation.
 */

@Service
public class JwtServiceImpl implements JwtService {
  public static final String JWT_SECRET = "D8803158EEEA9C8E0618E1DCA3E22945308d7703927815f91BA6f9C3E3B3B6D0";
  private static final long JWT_EXPIRATION_IN_MIN = 365L * 24L * 60L * 60L * 1000L;

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtServiceImpl.class);

  @Override
  public String extractUserEmail(String token) {
    Claims claims = extractAllClaims(token);
    return String.valueOf(claims.get("email"));
  }


  @Override
  public String generateTokenForAdmin(User user) {
    Set<String> permissions = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    return Jwts.builder()
            .setSubject(String.valueOf(user.getId()))
            .claim(Constant.EMAIL, user.getEmail())
            .claim(Constant.STATUS, user.getStatus())
            .claim(Constant.PERMISSION, permissions)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  @Override
  public String generateTokenForUser(User user) {
    Set<String> permissions = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    return Jwts.builder()
            .setSubject(String.valueOf(user.getId()))
            .claim(Constant.NAME, user.getFullName())
            .claim(Constant.EMAIL, user.getEmail())
            .claim(Constant.USER_REGISTER, user.getUserRegistered())
            .claim(Constant.STATUS, user.getStatus())
            .claim(Constant.PERMISSION, permissions)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  @Override
  public String generateRefreshToken(User user) {
    return Jwts.builder()
            .setSubject(String.valueOf(user.getId()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 48))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  @Override
  public String extractUserId(String token) {
    Claims claims = extractAllClaims(token);
    return String.valueOf(claims.get("sub"));
  }

  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String email = extractUserEmail(token);
    return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  @Override
  public boolean isRefreshTokenValid(String token) {
    Long userId = Long.valueOf(extractUserId(token));
    return (Objects.nonNull(userId) && !isTokenExpired(token));
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
    final Claims claims = extractAllClaims(token);
    return claimsResolvers.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    Claims claims = null;
    try {
      claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
              .getBody();
    } catch (Exception e) {
      LOGGER.error("Invalid Token, Error Message: {}", e.getMessage());
      throw new InvalidRequestException(MessageEnum.INVALID_TOKEN.name());
    }
    return claims;
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
}
