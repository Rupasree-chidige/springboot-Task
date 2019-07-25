package com.stackroute.userservice.exceptions;

public class TrackNotFoundException extends Exception {
    String message;

    public TrackNotFoundException() {
    }

    public TrackNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
