package com.example.basket.domain.basket.web;

import com.example.basket.domain.basket.api.BasketDto;
import com.example.basket.domain.basket.api.basketitem.BasketItemDto;
import com.example.basket.domain.basket.api.product.Product;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AddProductRequest {
    private int productId;
    private int count;
    private int customerId;
    public BasketDto toDto(){
        BasketItemDto dto = BasketItemDto.builder().product(new Product(this.productId)).build();
        dto.count = count;
        List<BasketItemDto> basketItemList = new ArrayList<>();
        basketItemList.add(dto);
        return BasketDto.builder()
                .userId(this.customerId)
                .basketItemList(basketItemList)
                .build();
    }
}
