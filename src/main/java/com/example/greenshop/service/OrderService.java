package com.example.greenshop.service;

import com.example.greenshop.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findOrdersByUser(int userId);
    Order findById(int id);
    void addOrders(Order order, int userId);
    void deleteById(int id);
    void update(int id, Order order);
}
