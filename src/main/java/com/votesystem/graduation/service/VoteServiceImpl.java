package com.votesystem.graduation.service;

import com.votesystem.graduation.model.Vote;
import com.votesystem.graduation.repository.RestaurantRepository;
import com.votesystem.graduation.repository.UserRepository;
import com.votesystem.graduation.repository.VoteRepository;
import com.votesystem.graduation.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.votesystem.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
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

}
