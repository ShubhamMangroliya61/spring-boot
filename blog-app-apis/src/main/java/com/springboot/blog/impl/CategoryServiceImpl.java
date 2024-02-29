package com.springboot.blog.impl;

import com.springboot.blog.entities.Category;
import com.springboot.blog.exceptions.ResourceNotFountException;
import com.springboot.blog.payloads.CategoryDto;
import com.springboot.blog.repositories.CategoryRepository;
import com.springboot.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category newCategory = this.categoryDtoToCategory(categoryDto);
        Category newAddedCategory =  categoryRepository.save(newCategory);
        return this.categoryToCategoryDto(newAddedCategory);
    }

    @Override
    public CategoryDto updateCategory(Integer catId, CategoryDto categoryDto) {
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(
                        () -> new ResourceNotFountException("Category", "Id",catId));

        if(categoryDto.getCategoryTitle() != null) category.setCategoryTitle(categoryDto.getCategoryTitle());
        if(categoryDto.getCategoryDescription() != null) category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = categoryRepository.save(category);

        return this.categoryToCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer catId) {
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(
                        () -> new ResourceNotFountException("Category", "Id",catId));

        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer catId) {
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(
                        () -> new ResourceNotFountException("Category", "Id",catId));

        return this.categoryToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();

        return allCategories
                .stream()
                .map(this::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    public Category categoryDtoToCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto, Category.class);
    }

    public CategoryDto categoryToCategoryDto(Category category){
        return this.modelMapper.map(category, CategoryDto.class);
    }
}
