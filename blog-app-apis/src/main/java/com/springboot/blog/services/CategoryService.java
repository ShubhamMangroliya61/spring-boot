package com.springboot.blog.services;

import com.springboot.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    // Create
    CategoryDto createCategory(CategoryDto categoryDto);

    // Update
    CategoryDto updateCategory(Integer catId, CategoryDto categoryDto);

    // Delete
    void deleteCategory(Integer catId);

    // Get
    CategoryDto getCategoryById(Integer catId);

    // Get all
    List<CategoryDto> getAllCategories();
}
