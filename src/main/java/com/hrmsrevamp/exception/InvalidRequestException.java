package com.hrmsrevamp.exception;

import org.springframework.http.HttpStatus;

/**
 * Invalid Request Exception.
 */
public class InvalidRequestException extends HttpException {
  public InvalidRequestException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
