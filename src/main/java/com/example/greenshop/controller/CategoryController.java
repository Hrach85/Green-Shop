package com.example.greenshop.controller;

import com.example.greenshop.entity.Category;
import com.example.greenshop.entity.Product;
import com.example.greenshop.security.CurrentUser;
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
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductService productService;
    private final CategoryService categoryService;


    @GetMapping
    public String categoryPage(ModelMap modelMap,
                               @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("categories", categoryService.findCategories());
        return "categories";
    }
    @GetMapping("/add")
    public String categoriesAddPage() {
        return "addCategories";
    }
    @PostMapping("/add")
    public String categoriesAdd(@ModelAttribute Category category) throws IOException {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/remove")
    public String removeCategory(@RequestParam("id") int id) {
        categoryService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String singleCategoryPagePost(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Category> byId = categoryService.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            List<Product> products = productService.findProducts();
            modelMap.addAttribute("category", category);
            modelMap.addAttribute("products", products);
            return "singleCategory";
        } else {
            return "redirect:/products";
        }
    }



}