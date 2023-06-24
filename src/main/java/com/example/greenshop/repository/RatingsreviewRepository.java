package com.example.greenshop.repository;

import com.example.greenshop.entity.Order;
import com.example.greenshop.entity.Ratingsreview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingsreviewRepository extends JpaRepository<Ratingsreview, Integer> {

    List<Ratingsreview> findAllByProductId(int id);
    Optional<Ratingsreview> findRatingsreviewByUserId(int id);

}
