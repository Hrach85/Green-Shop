package com.example.greenshop.service.impl;

import com.example.greenshop.dto.cartDto.CartDto;
import com.example.greenshop.entity.Cart;
import com.example.greenshop.entity.Product;
import com.example.greenshop.entity.Role;
import com.example.greenshop.entity.User;
import com.example.greenshop.mapper.CartMapper;
import com.example.greenshop.mapper.ProductMapper;
import com.example.greenshop.mapper.UserMapper;
import com.example.greenshop.repository.CartRepository;
import com.example.greenshop.repository.ProductRepository;
import com.example.greenshop.security.CurrentUser;
import com.example.greenshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    @Override
    public List<Cart> findCarts(User user) {
        List<Cart> carts;
        if (user.getRole() == Role.ADMIN) {
            carts = cartRepository.findAll();
        } else {
            carts = cartRepository.findAllByUserId(user.getId());
        }
        return carts;
    }

    @Override
    public Optional<Cart> findById(int id) {
        return cartRepository.findById(id);
    }

    @Override
    public void addCart(int productId, User user, int quantity) throws IOException {
        Optional<Product> byId = productRepository.findById(productId);
        if (byId.isPresent()) {
            Product product = byId.get();
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    @Override
    public void deleteById(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void updateCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<CartDto> findCartsByUser(User user) {
        List<Cart> carts;
        List<CartDto> cartDtos = new ArrayList<>();
        if (user.getRole() == Role.ADMIN) {
            carts = cartRepository.findAll();
            for (Cart cart : carts) {
                CartDto cartDto = cartMapper.mapToDto(cart);
                cartDto.setUserDto(userMapper.mapToDto(cart.getUser()));
                cartDto.setProductDto(productMapper.mapToDto(cart.getProduct()));
                cartDtos.add(cartDto);
            }
        } else {
            carts = cartRepository.findAllByUserId(user.getId());
            for (Cart cart : carts) {
                CartDto cartDto = cartMapper.mapToDto(cart);
                cartDto.setUserDto(userMapper.mapToDto(cart.getUser()));
                cartDto.setProductDto(productMapper.mapToDto(cart.getProduct()));
                cartDtos.add(cartDto);}
        }
        return cartDtos;
    }

    @Override
    public void updateCartByCurrentUser(int userId, int newQuantity, int cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setQuantity(newQuantity);
            cartRepository.save(cart);
        }
    }

    @Override
    public double calculateCurrentUserCartTotal(CurrentUser currentUser) {
        double productPriceTotal = 0.0;
        List<Cart> carts = cartRepository.findAllByUserId(currentUser.getUser().getId());

        for (Cart cart : carts) {
            double productPrice = cart.getProduct().getPrice();
            int quantity = cart.getQuantity();
            double subtotal = productPrice * quantity;
            productPriceTotal += subtotal;
        }
        return productPriceTotal;
    }

}
