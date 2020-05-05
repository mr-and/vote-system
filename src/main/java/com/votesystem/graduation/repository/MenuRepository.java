package com.votesystem.graduation.repository;

import com.votesystem.graduation.model.Menu;
import com.votesystem.graduation.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    String getAllByDate = "SELECT m FROM Menu m WHERE m.date=:date ORDER BY m.restaurant.id";
    String getAllByDateAndAndRestaurant = "SELECT m FROM Menu m WHERE m.date=?1 and m.restaurant=?2";


    @EntityGraph(attributePaths = {"restaurant", "dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(getAllByDate)
    List<Menu> getAllByDate(@Param("date") LocalDate date);

    @EntityGraph(attributePaths = {"restaurant", "dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(getAllByDateAndAndRestaurant)
    Optional<Menu> getAllByDateAndAndRestaurant(LocalDate date, Restaurant restaurant);

    Optional<Menu> findByDateAndRestaurant(LocalDate date, Restaurant restaurant);

}
