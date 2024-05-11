package com.hrmsrevamp.security;

import com.hrmsrevamp.entity.User;
import com.hrmsrevamp.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedInUser {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggedInUser.class);
  @Autowired
  private AuthenticationManager authenticationManager;

  public static final User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      LOGGER.error("Authentication in SecurityContextHolder is NULL");
      throw new UnauthorizedException("Unauthorized");
    }
    if (authentication.getPrincipal() != "anonymousUser") {
      try {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser();
      } catch (Exception e) {
        LOGGER.error("Error getting current User: {}", e.getMessage());
      }
    }
    return null;
  }
}

