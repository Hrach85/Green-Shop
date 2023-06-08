package com.example.greenshop.servise;

import com.example.greenshop.entity.Product;
import com.example.greenshop.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findProducts();
    Optional<Product> findById(int id);
    void addProduct(User currentUser, MultipartFile multipartFile, Product product) throws IOException;
    void deleteById(int id);


}
