package com.votesystem.graduation.service;

import com.votesystem.graduation.model.Restaurant;
import com.votesystem.graduation.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.votesystem.graduation.util.ValidationUtil.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getId(int restId){
        return checkNotFoundWithId(restaurantRepository.getOne(restId), restId);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Transactional
    @Override
    public void update(Restaurant restaurant, int restId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.findById(restId), restId);
        assureIdConsistent(restaurant, restId);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int restId){
        checkNotFoundWithId(restaurantRepository.findById(restId), restId);
        restaurantRepository.deleteById(restId);
    }
}
