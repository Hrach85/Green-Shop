package com.example.greenshop.service.impl;

import com.example.greenshop.entity.Cart;
import com.example.greenshop.entity.Order;
import com.example.greenshop.entity.Product;
import com.example.greenshop.entity.User;
import com.example.greenshop.repository.CartRepository;
import com.example.greenshop.repository.OrderRepository;
import com.example.greenshop.repository.ProductRepository;
import com.example.greenshop.repository.UserRepository;
import com.example.greenshop.service.CartService;
import com.example.greenshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;

    @Override
    public List<Order> findOrdersByUser(int userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            List<Order> orders = orderRepository.findAllByUserId(byId.get().getId());
            return orders;
        }
        return null;
    }

    @Override
    public Order findById(int id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    @Override
    public void addOrders(Order order, int userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            order.setUser(byId.get());
            orderRepository.save(order);
        }
        Optional<Cart> byProductId = cartRepository.findByProductId(order.getProduct().getId());
        if (byProductId.isPresent()) {
            Cart cart = byProductId.get();
            Optional<Product> byIdProd = productRepository.findById(cart.getProduct().getId());
            if (byIdProd.isPresent()) {
                Product product = byIdProd.get();
                product.setQuantity(product.getQuantity() - cart.getQuantity());
                productRepository.save(product);
            }
            cartService.deleteById(cart.getId());
        }
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void update(int id, Order order) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            Order orderDb = byId.get();
            orderDb.setUser(order.getUser());
            orderDb.setProduct(order.getProduct());
            orderDb.setOrderDate(order.getOrderDate());
            orderRepository.save(orderDb);
        }

    }
}
