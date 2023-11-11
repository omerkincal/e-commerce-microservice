package com.example.stock.domain.category.web;

import com.example.stock.domain.category.api.CategoryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {
    private String categoryName;

    public CategoryRequest(@JsonProperty("categoryName") String categoryName){
          this.categoryName=categoryName;
        }
    public CategoryDto toDto(){
        return CategoryDto.builder()
                .categoryName(this.categoryName)
                .build();
    }
}
