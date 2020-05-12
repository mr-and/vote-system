package com.votesystem.graduation.controller;


import com.votesystem.graduation.configuration.AdminAccess;
import com.votesystem.graduation.configuration.FullAccess;
import com.votesystem.graduation.model.Restaurant;
import com.votesystem.graduation.service.RestaurantService;
import com.votesystem.graduation.service.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.votesystem.graduation.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    static final String REST_URL = "/api/v1/restaurants";

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
//    https://stackoverflow.com/questions/5402723/spring-security-meta-annotation
    @FullAccess
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping(value = "/{id}")
    @FullAccess
    public Restaurant getOne(@PathVariable int id) {
        return checkNotFoundWithId(restaurantService.getId(id), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @AdminAccess
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        var saveInstance = restaurantService.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saveInstance.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(saveInstance);
    }

}
