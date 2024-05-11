package com.hrmsrevamp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Entity Not Found Exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends HttpException {

  private List<String> details;

  public EntityNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }

  public EntityNotFoundException(String message, List<String> details) {
    super(message, HttpStatus.NOT_FOUND);
    this.details = details;
  }

  public List<String> getDetails() {
    return details;
  }

}
