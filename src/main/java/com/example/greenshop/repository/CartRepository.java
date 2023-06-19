package com.example.greenshop.repository;

import com.example.greenshop.entity.Cart;
import com.example.greenshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserId(int id);
    Optional<Cart> findByUserIdAndProductId(int userId, int productId);

}
