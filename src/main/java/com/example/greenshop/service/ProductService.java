package com.example.greenshop.service;

import com.example.greenshop.dto.productDto.CreateProductRequestDto;
import com.example.greenshop.dto.productDto.ProductDto;
import com.example.greenshop.dto.productDto.UpdateProductRequestDto;
import com.example.greenshop.entity.Product;
import com.example.greenshop.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> findProducts();
    Optional<Product> findById(int id);
    void addProduct(User currentUser, MultipartFile multipartFile, CreateProductRequestDto createProductRequestDto) throws IOException;
    void deleteById(int id);
    public void updateProduct(User currentUser, MultipartFile multipartFile, UpdateProductRequestDto updateProductRequestDto) throws IOException;
    public ProductDto singleProduct(int id);
}
