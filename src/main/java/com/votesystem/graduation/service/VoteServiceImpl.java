package com.votesystem.graduation.service;

import com.votesystem.graduation.configuration.AuthUser;
import com.votesystem.graduation.model.Restaurant;
import com.votesystem.graduation.model.User;
import com.votesystem.graduation.model.Vote;
import com.votesystem.graduation.repository.RestaurantRepository;
import com.votesystem.graduation.repository.UserRepository;
import com.votesystem.graduation.repository.VoteRepository;
import com.votesystem.graduation.util.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.votesystem.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
@Slf4j
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private MenuService menuService;
    private RestaurantRepository restaurantRepository;

    public VoteServiceImpl(VoteRepository voteRepository,
                           UserRepository userRepository,
                           MenuService menuService,
                           RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.menuService = menuService;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Optional<Vote> get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(voteRepository.findById(id).filter(v -> v.getUser().getId() == userId), id);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return voteRepository.findByUserIdOrderByDateDesc(userId);
    }

    @Override
    public List<Vote> getBetweenDates(int userId, LocalDate startDate, LocalDate endDate) {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate  must not be null");
        return voteRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, startDate, endDate);
    }

    @Override
    public void doVote(AuthUser user, int restaurantId) throws Exception {

        if (LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            throw new Exception("Vote late");
        }

        Vote vote = voteRepository.findByUserId(user.getId());
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        Set<Vote> votes = restaurant.getVotes();
        if (votes.contains(vote)) {
            voteRepository.deleteById(vote.getId());
        } else {
            voteRepository.save(new Vote(LocalDate.now(), userRepository.findById(user.getId()).get()));
        }
        restaurantRepository.save(restaurant);
    }

}
