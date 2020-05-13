package com.votesystem.graduation.controller;

import com.votesystem.graduation.configuration.AdminAccess;
import com.votesystem.graduation.configuration.AuthAccess;
import com.votesystem.graduation.model.Menu;
import com.votesystem.graduation.service.MenuService;
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
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

    static final String REST_URL = "/api/v1/restaurants";

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @AuthAccess
    @GetMapping(value = {"/{restaurantId}/menu/{menuId}"})
    public Menu getOne(@PathVariable(value = "restaurantId") int restaurantId,
                       @PathVariable(value = "menuId") int menuId) {
        log.info("get menu {} for restaurant {}", menuId, restaurantId);
        return menuService.getMenuWithRestaurant(restaurantId, menuId);
    }

    @AuthAccess
    @GetMapping(value = {"/{id}/menu"})
    public List<Menu> getAll(@PathVariable(value = "id") int restaurantId) {
        log.info("get all menu {} for restaurant", restaurantId);
        return menuService.getAll(restaurantId);
    }

    @AdminAccess
    @PostMapping(value = {"/{restaurantId}/menu"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@PathVariable(value = "restaurantId") int restaurantId,
                                       @Valid @RequestBody Menu menu
    ) {
        log.info("create menu{} for restaurant{}", menu, restaurantId);
        var saveInstance = menuService.save(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saveInstance.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(saveInstance);
    }

    @AdminAccess
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = {"/{restaurantId}/menu/{menuId}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(value = "restaurantId") int restaurantId,
                       @PathVariable(value = "menuId") int menuId,
                       @Valid @RequestBody Menu menu) {
        log.info("update menu{} for restaurant{}", menu, restaurantId);
        menuService.update(restaurantId, menuId, menu);
    }
}
