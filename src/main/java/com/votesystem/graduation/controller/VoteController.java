package com.votesystem.graduation.controller;

import com.votesystem.graduation.configuration.AuthUser;
import com.votesystem.graduation.configuration.UserAccess;
import com.votesystem.graduation.exception.VoteTimeExpired;
import com.votesystem.graduation.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    static final String REST_URL = "/api/v1/restaurants";

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @UserAccess
    @RequestMapping(value = {"/{restaurantId}/votes"}, method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createOrUpdateVote(@AuthenticationPrincipal AuthUser user,
                                   @PathVariable(value = "restaurantId") int restaurantId) throws VoteTimeExpired {

        log.info("vote for restaurant {}", restaurantId);
        voteService.doVote(user, restaurantId);
    }
}
