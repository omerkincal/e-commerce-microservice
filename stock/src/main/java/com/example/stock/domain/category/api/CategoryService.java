package com.example.stock.domain.category.api;

import com.example.stock.domain.category.impl.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);
    CategoryDto getCategory(String categoryId);
    CategoryDto updateCategory(CategoryDto categoryDto, String categoryId);
    List<CategoryDto> getAllCategories();
    Category getCategoryEntity(String categoryId);
    CategoryDto toDto(Category category);
    void deleteCategory(String categoryId);
}
