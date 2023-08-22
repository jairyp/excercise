package com.devsu.exercise.exception;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class NewModelNotFoundException extends ErrorResponseException {

  public NewModelNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, asProblemDetail(message), null);
  }

  private static ProblemDetail asProblemDetail(String message) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
    problemDetail.setTitle("Model Not Found");
    problemDetail.setType(URI.create("/not-found"));
    problemDetail.setProperty("new value", "value test");
    problemDetail.setProperty("age", 32);
    return problemDetail;
  }
}
