package com.votesystem.graduation.service;

import com.votesystem.graduation.exception.CustomNotFound;
import com.votesystem.graduation.model.Menu;
import com.votesystem.graduation.repository.MenuRepository;
import com.votesystem.graduation.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.votesystem.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "restaurants", allEntries = true)
    public Menu save(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        var restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(()-> new CustomNotFound(restaurantId));

        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "restaurants", allEntries = true)
    public void update(int restaurantId, int menuId, Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        var menuWithRestaurant = getMenuWithRestaurant(restaurantId, menuId);
        menuWithRestaurant.setDate(menu.getDate());
        menuRepository.save(menuWithRestaurant);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        checkNotFoundWithId(restaurantRepository.findById(restaurantId), restaurantId);
        return menuRepository.getAllBy(restaurantId);
    }

    @Override
    public Menu getMenuWithRestaurant(int restaurantId, int menuId) {
        checkNotFoundWithId(restaurantRepository.findById(restaurantId), restaurantId);
        checkNotFoundWithId(menuRepository.findById(menuId), menuId);
        return menuRepository.getMenuWithRestaurant(restaurantId, menuId);
    }
}
