package com.disney.studios.errors;

public class DogDoesNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DogDoesNotExistException(String id) {
        super("Could not find Dog. " + id);
    }
}