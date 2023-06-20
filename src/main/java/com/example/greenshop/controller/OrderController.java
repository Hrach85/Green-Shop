package com.example.greenshop.controller;

import com.example.greenshop.entity.Cart;
import com.example.greenshop.entity.Order;
import com.example.greenshop.entity.User;
import com.example.greenshop.repository.UserRepository;
import com.example.greenshop.security.CurrentUser;
import com.example.greenshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;


    @GetMapping("/{id}")
    public String getOrderById(@PathVariable int id, ModelMap modelMap) {
        Order order = orderService.findById(id);
        modelMap.addAttribute("order", order);
        return "singleOrder";
    }

    @GetMapping
    public String getOrdersByUserId(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        List<Order> orders = orderService.findOrdersByUser(currentUser.getUser().getId());
        modelMap.addAttribute("orders", orders);
        return "orderPage";

    }

    @PostMapping
    public String addOrder(@RequestBody Order order, @RequestParam("userId") int userId) {
        orderService.addOrders(order, userId);
        return "redirect:/order";
    }

    @DeleteMapping("/{id}")
    public String deleteOrderById(@PathVariable int id) {
        orderService.deleteById(id);
        return "redirect:/order";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable int id, @ModelAttribute Order updatedOrder) {
        orderService.update(id, updatedOrder);
        return "redirect:/order/";
    }

}
