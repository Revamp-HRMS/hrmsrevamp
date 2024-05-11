package com.hrmsrevamp.exception;

import org.springframework.http.HttpStatus;

/**
 * Duplicate Entity Exception.
 */
public class DuplicateEntityException extends HttpException {
  public DuplicateEntityException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
