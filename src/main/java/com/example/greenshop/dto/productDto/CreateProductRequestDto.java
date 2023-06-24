package com.example.greenshop.dto.productDto;

import com.example.greenshop.dto.categoryDto.CategoryDto;
import com.example.greenshop.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequestDto {

    private int id;
    private String name;
    private  double price;
    private String description;
    private String image;
    private CategoryDto categoryDto;
    private int quantity;
    private Double rating;
}
