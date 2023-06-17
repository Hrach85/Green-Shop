package com.example.greenshop.service;

import com.example.greenshop.entity.Category;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findCategories();
    Optional<Category> findById(int id);

    void addCategory(Category category) throws IOException;

    void deleteById(int id);
    public void updateCategory(Category category);


}
