package com.example.basket.domain.basket.api.product;

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
    private int quantity;
    private double price;
    private long categoryId;

    public Product(int productId) {
        this.productId = productId;
    }
}
