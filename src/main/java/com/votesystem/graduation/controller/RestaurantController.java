package com.votesystem.graduation.controller;


import com.votesystem.graduation.configuration.AdminAccess;
import com.votesystem.graduation.configuration.AuthAccess;
import com.votesystem.graduation.exception.CustomNotFound;
import com.votesystem.graduation.model.Restaurant;
import com.votesystem.graduation.service.RestaurantService;
import com.votesystem.graduation.service.RestaurantServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    static final String REST_URL = "/api/v1/restaurants";

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    @AuthAccess
    @GetMapping(value = "/{restaurantId}")
    public Restaurant getOne(@PathVariable int restaurantId) {
        log.info("get restaurant {}", restaurantId);
        return restaurantService.findById(restaurantId).orElseThrow(() -> new CustomNotFound(restaurantId));
    }

    @AuthAccess
    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    @AdminAccess
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        log.info("create restaurant{}", restaurant);
        var saveInstance = restaurantService.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saveInstance.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(saveInstance);
    }

    @AdminAccess
    @DeleteMapping(value = "/{restaurantId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId) {
        log.info("delete restaurant {}", restaurantId);
        restaurantService.delete(restaurantId);
    }

}
