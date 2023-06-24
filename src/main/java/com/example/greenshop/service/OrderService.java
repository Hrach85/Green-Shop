package com.example.greenshop.service;

import com.example.greenshop.dto.orderDto.CreateOrderRequestDto;
import com.example.greenshop.dto.orderDto.OrderDto;
import com.example.greenshop.dto.orderDto.UpdateOrderRequestDto;
import com.example.greenshop.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderDto> findOrdersByUser(int userId);
    Order findById(int id);
    void addOrders(CreateOrderRequestDto createOrderRequestDto, int userId);
    void deleteById(int id);
    void update(int id, UpdateOrderRequestDto order);
    OrderDto getOrderById(int id);
}
