package com.votesystem.graduation.controller;

import com.votesystem.graduation.configuration.AdminAccess;
import com.votesystem.graduation.model.Dish;
import com.votesystem.graduation.service.DishService;
import com.votesystem.graduation.service.DishServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Slf4j
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {

    private static final String REST_URL = "/api/v1/restaurants";

    private final DishService dishService;

    public DishController(DishServiceImpl dishService) {
        this.dishService = dishService;
    }


    @PostMapping(value = {"/{restaurantId}/menu/{menuId}/dish"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @AdminAccess
    public ResponseEntity<Dish> create(@PathVariable(value = "restaurantId") int restaurantId,
                                       @PathVariable(value = "menuId") int menuId,
                                       @RequestBody Dish dish) {

        var saveInstance = dishService.saveWithRestaurantAndMenu(restaurantId, menuId, dish);
        log.info("New dish with id: {} created", dish.getId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saveInstance.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(saveInstance);

    }

    @PutMapping(value = {"/{restaurantId}/menu/{menuId}/dish/{dishId}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @AdminAccess
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "restaurantId") int restaurantId,
                       @PathVariable(value = "menuId") int menuId,
                       @PathVariable(value = "dishId") int dishId,
                       @RequestBody Dish dish) {

        log.info("Dish with id: {} updated", dish.getId());
        dishService.update(restaurantId, menuId, dishId, dish);
    }
}
