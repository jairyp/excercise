package com.devsu.exercise.exception;

import java.net.URI;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<CustomErrorResponse> handleAllException(Exception ex, WebRequest request) {
    CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ModelNotFoundException.class)
  public ErrorResponse handleModelNotFoundException(ModelNotFoundException ex, WebRequest req) {
    return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
        .title("Model not found")
        .type(URI.create(req.getContextPath()))
        .property("test", "value-test")
        .property("age", 32)
        .build();
  }

  @ExceptionHandler(SQLException.class)
  public ResponseEntity<CustomErrorResponse> handleSQLException(SQLException ex, WebRequest req) {
    CustomErrorResponse res = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),
        req.getDescription(false));
    return new ResponseEntity<>(res, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(CuentaException.class)
  public ResponseEntity<CustomErrorResponse> handleCuentaException(CuentaException ex,
      WebRequest req) {
    CustomErrorResponse res = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),
        req.getDescription(false));
    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    String msg = ex.getBindingResult().getAllErrors().stream().map(
        e -> e.getCode().concat(":").concat(e.getDefaultMessage())
    ).collect(Collectors.joining());

    CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), msg,
        request.getDescription(false));
    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
  }
}
