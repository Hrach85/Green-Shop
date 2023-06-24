package com.example.greenshop.mapper;

import com.example.greenshop.dto.productDto.CreateProductRequestDto;
import com.example.greenshop.dto.productDto.ProductDto;
import com.example.greenshop.dto.productDto.UpdateProductRequestDto;
import com.example.greenshop.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product map(CreateProductRequestDto dto);
    ProductDto mapToDto(Product entity);
    Product updateDto(UpdateProductRequestDto entity);
    Product dtoToMap(ProductDto entity);


}
