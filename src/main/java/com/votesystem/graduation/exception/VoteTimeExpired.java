package com.votesystem.graduation.exception;

public class VoteTimeExpired extends RuntimeException {
    public VoteTimeExpired() {
        super("votes are accepted until 11 o’clock");
    }
}
