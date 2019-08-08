package com.stackroute.track.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice("com.stackroute.userservice.controller")
class TrackControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {TrackNotFoundException.class})
  protected ResponseEntity<Object> NotFoundConflict(Exception ex, WebRequest request) {
    String bodyOfResponse = "Track not found!";
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {TrackAlreadyExistsException.class})
  protected ResponseEntity<Object> AlreadyExistsConflict(Exception ex, WebRequest request) {
    String bodyOfResponse = "Track already exists!";
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

}
