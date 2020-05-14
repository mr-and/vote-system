package com.votesystem.graduation.service;

import com.votesystem.graduation.configuration.AuthUser;
import com.votesystem.graduation.exception.VoteTimeExpired;
import com.votesystem.graduation.model.Vote;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface VoteService {

    Optional<Vote> get(int id, int userId);

    List<Vote> getAll(int userId);

    @Transactional
    void doVote(AuthUser user, int restaurantId) throws VoteTimeExpired;
}
