
package com.hrmsrevamp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Http exception is the general custom exception.
 */
@Getter
@Setter
@AllArgsConstructor
public class HttpException extends RuntimeException {
  private final HttpStatus status;

  public HttpException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return this.status;
  }
}
