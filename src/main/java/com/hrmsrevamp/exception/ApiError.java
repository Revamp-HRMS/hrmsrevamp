
package com.hrmsrevamp.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ApiError {

  private final String status;
  private final String message;
  private List<String> details;

  public ApiError(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public ApiError(String status, String message, List<String> details) {
    this.status = status;
    this.message = message;
    this.details = details;
  }
}
