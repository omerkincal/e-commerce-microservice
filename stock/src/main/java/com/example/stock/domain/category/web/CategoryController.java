package com.example.stock.domain.category.web;

import com.example.stock.domain.category.api.CategoryDto;
import com.example.stock.domain.category.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request){
        CategoryDto categoryDto = request.toDto();
        categoryDto = service.saveCategory(categoryDto);
        return new ResponseEntity<>(toResponse(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable(name = "id") String categoryId){
        return new ResponseEntity<>(toResponse(service.getCategory(categoryId)), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest request,
                                                           @PathVariable(name = "id") String categoryId){
        return new ResponseEntity<>(toResponse(service.updateCategory(request.toDto(),categoryId)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id")String categoryId){
        try {
            service.deleteCategory(categoryId);
            return new ResponseEntity<>("Successfully deleted :)", HttpStatus.OK);
        }
        catch (Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return new ResponseEntity<>(service.getAllCategories()
                .stream()
                .map(categoryDto -> toResponse(categoryDto))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    private CategoryResponse toResponse(CategoryDto categoryDto) {
        return CategoryResponse.builder()
                .categoryId(categoryDto.getCategoryId())
                .categoryName(categoryDto.getCategoryName())
                .build();
    }
}
