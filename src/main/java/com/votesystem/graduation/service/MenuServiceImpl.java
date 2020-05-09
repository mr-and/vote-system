package com.votesystem.graduation.service;

import com.votesystem.graduation.model.Menu;
import com.votesystem.graduation.model.Restaurant;
import com.votesystem.graduation.repository.MenuRepository;
import com.votesystem.graduation.repository.RestaurantRepository;
import com.votesystem.graduation.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.votesystem.graduation.util.ValidationUtil.checkNew;
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
    public Menu save(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        checkNew(menu);
        final Restaurant restaurant = checkNotFoundWithId(restaurantRepository.findById(restaurantId), restaurantId).orElse(null);
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    @Override
    public void update(int restaurantId, int menuId, Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        var menuWithRestaurant = getMenuWithRestaurant(restaurantId, menuId);
        menuWithRestaurant.setDate(menu.getDate());
        menuRepository.save(menuWithRestaurant);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return menuRepository.getAllBy(restaurantId);
    }

    @Override
    public Menu getMenuWithRestaurant(int restaurantId, int menuId) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.findById(restaurantId), restaurantId).get();
        return menuRepository.getMenuWithRestaurant(restaurantId, menuId);
    }


}
