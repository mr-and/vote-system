package com.votesystem.graduation.service;

import com.votesystem.graduation.model.Dish;
import com.votesystem.graduation.repository.DishRepository;
import com.votesystem.graduation.repository.MenuRepository;
import com.votesystem.graduation.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository, MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Dish saveWithRestaurantAndMenu(int restaurantId, int menuId, Dish dish) {
        var menu = menuRepository.findById(menuId);
        menu.setRestaurant(restaurantRepository.findById(restaurantId).get());
        dish.setMenu(menu);
        return dishRepository.save(dish);
    }

    @Override
    public void update(int restaurantId, int menuId, int dishId, Dish dish) {
        var instanceDish = dishRepository.findById(dishId).get();
        var instanceMenu = menuRepository.findById(menuId);
        instanceMenu.setRestaurant(restaurantRepository.findById(restaurantId).get());
        instanceDish.setMenu(instanceMenu);
        instanceDish.setName(dish.getName());
        instanceDish.setPrice(dish.getPrice());
        dishRepository.save(instanceDish);
    }


}
