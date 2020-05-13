package com.votesystem.graduation.repository;

import com.votesystem.graduation.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> findByUserIdAndRestaurantId(int userId, int restaurantId);

    List<Vote> findByUserIdOrderByDateDesc(int userId);

}
