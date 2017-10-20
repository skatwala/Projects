package com.disney.studios.errors;

public class UserAlreadyVotedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserAlreadyVotedException(String id) {
        super("User has already voted " + id);
    }
}