package com.example.greenshop.controller;

import com.example.greenshop.entity.Cart;
import com.example.greenshop.entity.Product;
import com.example.greenshop.entity.User;
import com.example.greenshop.security.CurrentUser;
import com.example.greenshop.service.CartService;
import com.example.greenshop.service.CategoryService;
import com.example.greenshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity,
                            @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        cartService.addCart(productId, currentUser.getUser(), quantity);
        return "redirect:/cart";
    }

    @GetMapping("/remove")
    public String removeCart(@RequestParam("id") int id) {
        cartService.deleteById(id);
        return "redirect:/cart";
    }

    @PostMapping("/update")

    public String updateToCart(@RequestParam("cartId") int cartId,
                               @RequestParam("quantity") int quantity,
                               @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        cartService.updateCartByCurrentUser(currentUser.getUser().getId(), quantity,cartId);
        return "redirect:/cart";
    }

    @GetMapping
    public String cartPage(ModelMap modelMap,
                           @AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        List<Cart> carts = cartService.findCartsByUser(user);
        double subtotal = cartService.calculateCurrentUserCartTotal(currentUser);

        modelMap.addAttribute("carts", carts);
        modelMap.addAttribute("subtotal", subtotal);

        return "shoppingCart";
    }
}