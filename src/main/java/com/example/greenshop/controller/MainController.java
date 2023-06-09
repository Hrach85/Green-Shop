package com.example.greenshop.controller;

import com.example.greenshop.dto.categoryDto.CategoryDto;
import com.example.greenshop.dto.productDto.ProductDto;
import com.example.greenshop.entity.Role;
import com.example.greenshop.entity.User;
import com.example.greenshop.security.CurrentUser;
import com.example.greenshop.service.CategoryService;
import com.example.greenshop.service.ProductService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MainController {

    @Value("${green_shop.upload.image.path}")
    private String imageUploadPath;
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/customLogin")
    public String customLogin() {
        return "customLoginPage";
    }

    @GetMapping("/customSuccessLogin")
    public String customSuccessLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null && currentUser.getUser() != null) {
            User user = currentUser.getUser();
            if (user.getRole() == Role.ADMIN) {
                return "redirect:/user/admin";
            } else if (user.getRole() == Role.CUSTOMER) {
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
        List<ProductDto> products = productService.findProducts();
        List<CategoryDto> categories = categoryService.findCategories();
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("products", products);
        return "admin";
    }

}

