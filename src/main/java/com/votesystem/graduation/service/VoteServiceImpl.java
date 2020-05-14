package com.votesystem.graduation.service;

import com.votesystem.graduation.configuration.AuthUser;
import com.votesystem.graduation.exception.CustomNotFound;
import com.votesystem.graduation.exception.VoteTimeExpired;
import com.votesystem.graduation.model.Restaurant;
import com.votesystem.graduation.model.Vote;
import com.votesystem.graduation.repository.RestaurantRepository;
import com.votesystem.graduation.repository.UserRepository;
import com.votesystem.graduation.repository.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.votesystem.graduation.util.ValidationUtil.checkNotFoundWithId;

@Slf4j
@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Optional<Vote> get(int id, int userId) {
        return checkNotFoundWithId(voteRepository.findById(id).filter(v -> v.getUser().getId() == userId), id);
    }

    @Override
    public List<Vote> getAll(int userId) {
        checkNotFoundWithId(userRepository.findById(userId), userId);
        return voteRepository.findByUserIdOrderByDateDesc(userId);
    }

    @Override
    @Transactional
    public void doVote(AuthUser user, int restaurantId) throws VoteTimeExpired {

        if (LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            throw new VoteTimeExpired();
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomNotFound(restaurantId));

        Vote vote = voteRepository.findByUserIdAndRestaurantId(user.getId(), restaurantId);

        Set<Vote> votes = restaurant.getVotes();

        if (votes.contains(vote)) {
            voteRepository.deleteById(vote.getId());
        } else {
            voteRepository.save(new Vote(LocalDate.now(), userRepository.findById(user.getId()).get(), restaurant));
        }
    }
}
