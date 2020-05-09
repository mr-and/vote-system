package com.votesystem.graduation.service;

import com.votesystem.graduation.model.Dish;

public interface DishService {

    Dish saveWithRestaurantAndMenu(int restaurantId, int menuId, Dish dish);

    void update(int restaurantId, int menuId, int dishId, Dish dish);

}
