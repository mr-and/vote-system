package com.votesystem.graduation.repository;

import com.votesystem.graduation.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Override
    @Transactional
    <S extends Restaurant> S save(S entity);

    @Override
    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Restaurant> findAll();

    @Override
    @Transactional
    void deleteById(Integer integer);
}
