package com.votesystem.graduation.controller;

import com.votesystem.graduation.model.Restaurant;
import com.votesystem.graduation.service.RestaurantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("RestaurantControllerTest")
class RestaurantControllerTest {

    @MockBean
    RestaurantService restaurantService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user.one@mail.ru")
    @DisplayName("GET /restaurant/1")
    void testGetSomeRestaurant() throws Exception {
        Optional<Restaurant> mockRestaurant = Optional.of(new Restaurant(1, "TestRestaurant"));

        Mockito.when(restaurantService.findById(1)).thenReturn(mockRestaurant);

        mockMvc.perform(get("/api/v1/restaurants/{restaurantId}", 1))
                .andExpect(status().isOk());
    }

}