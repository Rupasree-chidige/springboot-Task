package com.stackroute.muzix.exceptions;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class GlobalExceptionHandler{
        //For handling track not found exception
        @ExceptionHandler(TrackNotFoundException.class)
        public ResponseEntity <VndErrors> notFoundException(final TrackNotFoundException e) {
            return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }
        //For response
        private ResponseEntity < VndErrors > error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
            final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
            return new ResponseEntity < > (new VndErrors(logRef, message), httpStatus);
        }
        //For handling track already exists exception
        @ExceptionHandler(TrackAlreadyExistsException.class) public ResponseEntity< VndErrors > assertionException(final TrackAlreadyExistsException e) {
            return error(e, HttpStatus.ALREADY_REPORTED, e.getLocalizedMessage());
        }
    }

