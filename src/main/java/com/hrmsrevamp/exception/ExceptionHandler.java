
package com.hrmsrevamp.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@ResponseBody
public class ExceptionHandler extends ResponseEntityExceptionHandler {
  private static final Logger EXCEPTION_LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

  @org.springframework.web.bind.annotation.ExceptionHandler(HttpException.class)
  public ResponseEntity<Object> handleHttpException(HttpException ex) {
    EXCEPTION_LOGGER.error(ex.getMessage(), ex);
    ApiError apiError = new ApiError(ex.getStatus().name(), ex.getMessage());
    return ResponseEntity.status(ex.getStatus()).body(apiError);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleServerException(Exception ex) {
    EXCEPTION_LOGGER.error(ex.getMessage(), ex);
    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
    EXCEPTION_LOGGER.error(ex.getMessage(), ex);
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.name(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
  public final ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
    EXCEPTION_LOGGER.error(ex.getMessage(), ex);
    List<String> details = ex.getConstraintViolations()
            .parallelStream()
            .map(ConstraintViolation::getMessage)
            .toList();
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.name(), "Validation Failed", details);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
  }

}
