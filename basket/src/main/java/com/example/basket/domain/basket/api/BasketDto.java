package com.example.basket.domain.basket.api;

import com.example.basket.domain.basket.api.basketitem.BasketItemDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BasketDto {
    private long basketId;
    private double totalAmount;
    private int status;
    private long userId;
    private List<BasketItemDto> basketItemList;
}
