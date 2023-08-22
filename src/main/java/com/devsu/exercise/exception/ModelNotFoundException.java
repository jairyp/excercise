package com.devsu.exercise.exception;

public class ModelNotFoundException extends RuntimeException {

  public ModelNotFoundException(String message) {
    super(message);
  }
}
