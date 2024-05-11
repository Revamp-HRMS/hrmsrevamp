package com.hrmsrevamp.service.impl;

import com.hrmsrevamp.entity.User;
import com.hrmsrevamp.exception.HttpException;
import com.hrmsrevamp.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * LoggedIn User.
 */
public class LoggedInUser {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggedInUser.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            LOGGER.error("Authentication in SecurityContextHolder is NULL");
            throw new HttpException("Unauthorized", HttpStatus.UNAUTHORIZED);
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

