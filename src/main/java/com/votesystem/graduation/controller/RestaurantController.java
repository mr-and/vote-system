package com.votesystem.graduation.controller;


import com.votesystem.graduation.model.Restaurant;
import com.votesystem.graduation.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {


    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant getOne(@PathVariable int id) {
        return restaurantService.getId(id);
    }

}
