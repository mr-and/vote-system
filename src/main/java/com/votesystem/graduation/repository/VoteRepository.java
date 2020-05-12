package com.votesystem.graduation.repository;

import com.votesystem.graduation.model.User;
import com.votesystem.graduation.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> findByUserIdAndDate(int userId, LocalDate date);

    void deleteById(int voteId);

    Vote findByUserId(int userId);

    List<Vote> findByUserIdOrderByDateDesc(int userId);

    List<Vote> findByUserIdAndDateBetweenOrderByDateDesc(int userId, LocalDate startDate, LocalDate endDate);
}
