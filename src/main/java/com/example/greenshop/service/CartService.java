package com.example.greenshop.service;

import com.example.greenshop.entity.Cart;
import com.example.greenshop.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CartService {

    List<Cart> findCarts(User user);
    Optional<Cart> findById(int id);

    void addCart(int productId,User user,int quantity) throws IOException;

    void deleteById(int id);

    public void updateCart(Cart cart);

}
