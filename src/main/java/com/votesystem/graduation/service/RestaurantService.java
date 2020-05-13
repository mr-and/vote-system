package com.votesystem.graduation.service;

import com.votesystem.graduation.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    Optional<Restaurant> findById(int id);

    List<Restaurant> getAll();

    Restaurant save(Restaurant restaurant);

    void delete(int restaurantId);
}
