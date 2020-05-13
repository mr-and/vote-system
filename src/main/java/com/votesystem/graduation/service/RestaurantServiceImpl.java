package com.votesystem.graduation.service;

import com.votesystem.graduation.exception.CustomNotFound;
import com.votesystem.graduation.model.Restaurant;
import com.votesystem.graduation.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static com.votesystem.graduation.util.ValidationUtil.checkNew;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Optional<Restaurant> findById(int restaurantId) {
        return Optional.ofNullable(restaurantRepository.findById(restaurantId).orElseThrow(() -> new CustomNotFound(restaurantId)));
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int restaurantId) {
        restaurantRepository.findById(restaurantId).orElseThrow(() -> new CustomNotFound(restaurantId));
        restaurantRepository.deleteById(restaurantId);
    }
}
