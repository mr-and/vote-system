package com.votesystem.graduation.service;

import com.votesystem.graduation.model.Vote;
import com.votesystem.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteService {

    Optional<Vote> get(int id, int userId) throws NotFoundException;

    List<Vote> getAll(int userId);

    List<Vote> getBetweenDates(int userId, LocalDate startDate, LocalDate endDate);
}
