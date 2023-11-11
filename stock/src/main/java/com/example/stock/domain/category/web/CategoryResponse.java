package com.example.stock.domain.category.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private long categoryId;
    private String categoryName;
}
