package com.springboot.blog.controllers;

import com.springboot.blog.payloads.CategoryDto;
import com.springboot.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto newCategoryDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(newCategoryDto, HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
        CategoryDto updatedCategory = categoryService.updateCategory(catId, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    // Delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer catId){
        categoryService.deleteCategory(catId);
        return new ResponseEntity<>(Map.of("Message", "Category deleted successfully"), HttpStatus.OK);
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // Get
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getUser(@PathVariable Integer catId){
        return ResponseEntity.ok(categoryService.getCategoryById(catId));
    }
}
