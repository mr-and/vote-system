package com.votesystem.graduation.repository;

import com.votesystem.graduation.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Override
    <S extends Dish> S save(S entity);

//    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restId ORDER BY d.name ASC")
//    List<Dish> findAllByMenuIdOrderById(int restId);

//    Dish findDishByIdAndRestaurantId(int id, int restId);
}
