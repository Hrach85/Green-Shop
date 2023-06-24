package com.example.greenshop.controller;

import com.example.greenshop.dto.categoryDto.CategoryDto;
import com.example.greenshop.dto.categoryDto.CreateCategoryRequestDto;
import com.example.greenshop.dto.productDto.ProductDto;
import com.example.greenshop.entity.Category;
import com.example.greenshop.entity.Product;
import com.example.greenshop.mapper.CategoryMapper;
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
    public String categoriesAdd(@ModelAttribute CreateCategoryRequestDto createCategoryRequestDto) throws IOException {
        categoryService.addCategory(createCategoryRequestDto);
        return "redirect:/categories";
    }

    @GetMapping("/remove")
    public String removeCategory(@RequestParam("id") int id) {
        categoryService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String singleCategoryPagePost(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("category",categoryService.singleCategoryPage(id));
        modelMap.addAttribute("products", productService.findProducts());
        return "singleCategory";
    }

}