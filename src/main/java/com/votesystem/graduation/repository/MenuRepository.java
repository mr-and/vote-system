package com.votesystem.graduation.repository;

import com.votesystem.graduation.model.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    String getAll = "SELECT m FROM Menu m WHERE m.restaurant.id=:id";
    String getMenuWithRestaurant = "SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId and m.id=:menuId";

    @EntityGraph(attributePaths = {"restaurant", "dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(getAll)
    List<Menu> getAllBy(@Param("id") int id);

    @EntityGraph(attributePaths = {"restaurant", "dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(getMenuWithRestaurant)
    Menu getMenuWithRestaurant(@Param("restaurantId") int restaurantId, @Param("menuId") int menuId);

}
