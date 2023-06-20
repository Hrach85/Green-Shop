package com.example.greenshop.controller;

import com.example.greenshop.dto.cartDto.CartDto;
import com.example.greenshop.dto.categoryDto.CategoryDto;
import com.example.greenshop.dto.productDto.ProductDto;
import com.example.greenshop.entity.Cart;
import com.example.greenshop.entity.User;
import com.example.greenshop.security.CurrentUser;
import com.example.greenshop.service.CartService;
import com.example.greenshop.service.CategoryService;
import com.example.greenshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class MyControllerAdvice {

    private final CartService cartService;
    private final CategoryService categoryService;
    private final ProductService productService;


    @ModelAttribute("currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            return currentUser.getUser();
        }
        return null;
    }

    @ModelAttribute("carts")
    public List<CartDto> cartUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            List<CartDto> carts = cartService.findCartsByUser(user);
            if (carts != null) {
                return carts;
            }
        }
        return null;
    }

    @ModelAttribute("subtotal")
    public Double subtotal(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            double subtotal = cartService.calculateCurrentUserCartTotal(currentUser);
            return subtotal;

        }
        return null;
    }

    @ModelAttribute("categories")
    public List<CategoryDto> allCategories() {
        return categoryService.findCategories();
    }

    @ModelAttribute("products")
    public List<ProductDto> allProducts() {
        return productService.findProducts();
    }
}
