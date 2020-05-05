package com.votesystem.graduation.service;

import com.votesystem.graduation.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();

    Restaurant getId (int restId);

    Restaurant create (Restaurant restaurant);

    void update(Restaurant restaurant, int id);

    void delete(int id);

}
