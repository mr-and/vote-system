package com.votesystem.graduation.controller;

import com.votesystem.graduation.configuration.AdminAccess;
import com.votesystem.graduation.configuration.FullAccess;
import com.votesystem.graduation.model.Menu;
import com.votesystem.graduation.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

    static final String REST_URL = "/api/v1/restaurants";

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    @GetMapping(value = {"/{id}/menu"})
    @FullAccess
    public List<Menu> getAll(@PathVariable(value = "id") int restaurantId) {
        return menuService.getAll(restaurantId);
    }

    @GetMapping(value = {"/{restaurantId}/menu/{menuId}"})
    public Menu getOne(@PathVariable(value = "restaurantId") int restaurantId,
                       @PathVariable(value = "menuId") int menuId) {

        return menuService.getMenuWithRestaurant(restaurantId, menuId);
    }

    @PostMapping(value = {"/{id}/menu"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @AdminAccess
    public ResponseEntity<Menu> create(@PathVariable(value = "id") int restaurantId, @RequestBody Menu menu) {
        var saveInstance = menuService.save(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saveInstance.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(saveInstance);
    }

    @PutMapping(value = {"/{restaurantId}/menu/{menuId}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @AdminAccess
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "restaurantId") int restaurantId,
                       @PathVariable(value = "menuId") int menuId,
                       @RequestBody Menu menu) {

        menuService.update(restaurantId, menuId, menu);
    }
}
