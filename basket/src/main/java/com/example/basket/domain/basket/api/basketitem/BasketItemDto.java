package com.example.basket.domain.basket.api.basketitem;

import com.example.basket.domain.basket.api.product.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class BasketItemDto {
    public long basketItemId;
    public double basketItemAmount;
    public int count;
    public Product product;
}
