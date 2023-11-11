package com.example.basket.domain.basket.api.product;

import com.example.basket.domain.basket.api.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long productId;
    private String name;
    private int stock;
    private double price;
    private Category category;

    public Product(int productId) {
        this.productId = productId;
    }
}
