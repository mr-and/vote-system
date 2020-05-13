package com.votesystem.graduation.exception;

public class CustomNotFound extends RuntimeException {

    public CustomNotFound(int id) {
        super("Not found: " + id);
    }

    public CustomNotFound(String arg) {
        super("Not found");
    }

}
