package com.votesystem.graduation.service;

import com.votesystem.graduation.exception.CustomNotFound;
import com.votesystem.graduation.model.Dish;
import com.votesystem.graduation.repository.DishRepository;
import com.votesystem.graduation.repository.MenuRepository;
import com.votesystem.graduation.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.votesystem.graduation.util.ValidationUtil.checkNotFoundWithId;

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
    @Transactional
    @CacheEvict(cacheNames = "restaurants", allEntries = true)
    public Dish saveWithRestaurantAndMenu(int restaurantId, int menuId, Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        var menu = menuRepository.findById(menuId).orElseThrow(()-> new CustomNotFound(menuId));
        menu.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(()-> new CustomNotFound(restaurantId)));
        dish.setMenu(menu);
        return dishRepository.save(dish);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "restaurants", allEntries = true)
    public void update(int restaurantId, int menuId, int dishId, Dish dish) {
        var instanceDish = dishRepository.findById(dishId).orElseThrow(()-> new CustomNotFound(dishId));
        var instanceMenu = menuRepository.findById(menuId).orElseThrow(()-> new CustomNotFound(menuId));
        instanceMenu.setRestaurant(checkNotFoundWithId(restaurantRepository.findById(restaurantId), restaurantId).get());
        instanceDish.setMenu(instanceMenu);
        instanceDish.setName(dish.getName());
        instanceDish.setPrice(dish.getPrice());
        dishRepository.save(instanceDish);
    }
}
