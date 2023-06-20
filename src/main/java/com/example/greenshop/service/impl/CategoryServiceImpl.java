package com.example.greenshop.service.impl;

import com.example.greenshop.dto.categoryDto.CategoryDto;
import com.example.greenshop.dto.categoryDto.CreateCategoryRequestDto;
import com.example.greenshop.entity.Category;
import com.example.greenshop.mapper.CategoryMapper;
import com.example.greenshop.repository.CategoryRepository;
import com.example.greenshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            CategoryDto categoryDto = categoryMapper.mapToDto(category);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void addCategory(CreateCategoryRequestDto createCategoryRequestDto) throws IOException {
        Category category = categoryMapper.map(createCategoryRequestDto);
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    public CategoryDto singleCategoryPage(int id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            return categoryMapper.mapToDto(byId.get());
        }return null;
    }

}
