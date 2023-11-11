package com.example.stock.domain.category.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private long categoryId;
    private String categoryName;
}
