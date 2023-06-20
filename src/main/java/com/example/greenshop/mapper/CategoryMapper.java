package com.example.greenshop.mapper;


import com.example.greenshop.dto.categoryDto.CategoryDto;
import com.example.greenshop.dto.categoryDto.CreateCategoryRequestDto;
import com.example.greenshop.dto.categoryDto.UpdateCategoryRequestDto;
import com.example.greenshop.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category map(CreateCategoryRequestDto dto);
    CategoryDto mapToDto(Category entity);
    UpdateCategoryRequestDto updateDto(Category entity);

}
