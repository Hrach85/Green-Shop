package com.example.greenshop.dto.productDto;

import com.example.greenshop.dto.categoryDto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private int id;
    private String name;
    private  double price;
    private String description;
    private String image;
    private CategoryDto categoryDto;
    private int quantity;

}
