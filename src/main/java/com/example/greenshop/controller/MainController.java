package com.example.greenshop.controller;

import com.example.greenshop.entity.Category;
import com.example.greenshop.entity.Product;
import com.example.greenshop.entity.Role;
import com.example.greenshop.entity.User;
import com.example.greenshop.security.CurrentUser;
import com.example.greenshop.service.CategoryService;
import com.example.greenshop.service.ProductService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller

public class MainController {

    @Value("${green_shop.upload.image.path}")
    private String imageUploadPath;
    private final CategoryService categoryService;
    private final ProductService productService;

    public MainController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String main() {
        return "index";
    }



    @GetMapping("/customLogin")
    public String customLogin(){
        return "customLoginPage";
    }

    @GetMapping("/customSuccessLogin")
    public String customSuccessLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if(user.getRole() == Role.ADMIN){
                return "redirect:/user/admin";
            }else if(user.getRole() == Role.CUSTOMER){
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    @GetMapping(value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("profilePic") String profilePic) throws IOException {
        File file = new File(imageUploadPath + profilePic);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }
    @GetMapping("/admin")
    public String adminPage(ModelMap modelMap) {
        List<Product> products = productService.findProducts();
        List<Category> categories = categoryService.findCategories();
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("products", products);
        return "admin";
    }

}

