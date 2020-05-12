package com.votesystem.graduation.service;

import com.votesystem.graduation.model.Menu;

import java.util.List;

public interface MenuService {

    Menu save(Menu menu, int restaurantId);

    void update(int restaurantId, int menuId, Menu menu);

    List<Menu> getAll(int restaurantId);

    Menu getMenuWithRestaurant(int restaurantId, int menuId);

    Menu findById(int menuId);

}
