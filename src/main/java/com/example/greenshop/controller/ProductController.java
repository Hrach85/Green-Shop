package com.example.greenshop.controller;

import com.example.greenshop.dto.productDto.CreateProductRequestDto;
import com.example.greenshop.dto.productDto.ProductDto;
import com.example.greenshop.dto.productDto.UpdateProductRequestDto;
import com.example.greenshop.dto.ratingsreviewDto.RatingsreviewDto;
import com.example.greenshop.security.CurrentUser;
import com.example.greenshop.service.CategoryService;
import com.example.greenshop.service.ProductService;
import com.example.greenshop.service.RatingsreviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final RatingsreviewService ratingsreviewService;

    @GetMapping("/{id}")
    public String singleProductPage(@PathVariable("id") int id, ModelMap modelMap) {
        List<RatingsreviewDto> allByProductId = ratingsreviewService.getAllByProductId(id);
        modelMap.addAttribute("reviews",allByProductId);
        modelMap.addAttribute("ratings",ratingsreviewService.calculateProductRating(allByProductId));
        modelMap.addAttribute("product", productService.singleProduct(id));
        return "singleProduct";
    }

    @GetMapping
    public String productPage(ModelMap modelMap,
                              @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("products", ratingsreviewService.allProductsRating());
        modelMap.addAttribute("categories", categoryService.findCategories());
        return "products";
    }

    @GetMapping("/add")
    public String productsAddPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findCategories());
        return "addProducts";
    }

    @PostMapping("/add")
    public String productsAdd(@ModelAttribute CreateProductRequestDto createProductRequestDto,
                              @RequestParam("image") MultipartFile multipartFile,
                              @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        productService.addProduct(currentUser.getUser(), multipartFile, createProductRequestDto);
        return "redirect:/products";
    }

    @GetMapping("/remove")
    public String removeProduct(@RequestParam("id") int id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @PostMapping("/update")
    public String productsUpdate(@ModelAttribute UpdateProductRequestDto updateProductRequestDto,
                                 @RequestParam("image") MultipartFile multipartFile,
                                 @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        productService.updateProduct(currentUser.getUser(), multipartFile, updateProductRequestDto);
        return "redirect:/products";
    }

}

