package com.example.stock.domain.category.impl;

import com.example.stock.domain.category.api.CategoryDto;
import com.example.stock.domain.category.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = toEntity(categoryDto);
        category = repository.save(category);
        return toDto(category);
    }

    @Override
    public CategoryDto getCategory(String categoryId) {
        Category category = getCategoryEntity(categoryId);
        return toDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        Category category = getCategoryEntity(categoryId);
        category.setCategoryName(categoryDto.getCategoryName());
        category = repository.save(category);
        return toDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(category -> toDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = getCategoryEntity(categoryId);
        repository.delete(category);
    }



    public CategoryDto toDto(Category entity){
        return CategoryDto.builder()
                .categoryId(entity.getCategoryId())
                .categoryName(entity.getCategoryName())
                .build();
    }

    public Category toEntity(CategoryDto dto){
        Category entity = new Category();
        entity.setCategoryName(dto.getCategoryName());
        return entity;
    }


    @Override
    public Category getCategoryEntity(String categoryId){
        return repository.findById(Long.valueOf(categoryId)).get();
    }
}
