package com.example.greenshop.controller;

import com.example.greenshop.entity.Product;
import com.example.greenshop.security.CurrentUser;
import com.example.greenshop.service.CategoryService;
import com.example.greenshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public String singleProductPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Product> byId = productService.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            modelMap.addAttribute("product", product);

            return "singleProduct";
        } else {
            return "redirect:/products";
        }

    }

    @GetMapping
    public String productPage(ModelMap modelMap,
                              @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("products", productService.findProducts());
        modelMap.addAttribute("categories", categoryService.findCategories());
        return "products";
    }
    @GetMapping("/add")
    public String productsAddPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findCategories());
        return "addProducts";
    }
    @PostMapping("/add")
    public String productsAdd(@ModelAttribute Product product,
                              @RequestParam("image") MultipartFile multipartFile,
                              @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        productService.addProduct(currentUser.getUser(), multipartFile, product);
        return "redirect:/products";
    }
    @GetMapping("/remove")
    public String removeProduct(@RequestParam("id") int id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}

