package com.example.basket.domain.basket.web;

import com.example.basket.domain.basket.api.basketitem.BasketItemDto;
import com.example.basket.domain.basket.api.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BasketResponse {
    private long basketId;
    private double totalAmount;
    private int status;
    private User user;
    private List<BasketItemDto> basketItemList;
}
