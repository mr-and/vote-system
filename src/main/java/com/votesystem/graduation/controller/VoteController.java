package com.votesystem.graduation.controller;

import com.votesystem.graduation.configuration.AuthUser;
import com.votesystem.graduation.configuration.FullAccess;
import com.votesystem.graduation.configuration.UserAccess;
import com.votesystem.graduation.model.User;
import com.votesystem.graduation.repository.UserRepository;
import com.votesystem.graduation.service.MenuService;
import com.votesystem.graduation.service.RestaurantService;
import com.votesystem.graduation.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VoteController {

    static final String REST_URL = "/api/v1/restaurants";

    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final VoteService voteService;
    private final UserRepository userRepository;

    @Autowired
    public VoteController(RestaurantService restaurantService, MenuService menuService, VoteService voteService, UserRepository userRepository) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.voteService = voteService;
        this.userRepository = userRepository;
    }

//    @RequestMapping(value = {"/{restaurantId}/votes"}, method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping(value = {"/{restaurantId}/votes"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @FullAccess
    public void createOrUpdateVote(@AuthenticationPrincipal AuthUser user,
                                   @PathVariable(value = "restaurantId") int restaurantId) throws Exception {

        voteService.doVote(user, restaurantId);
    }
}
