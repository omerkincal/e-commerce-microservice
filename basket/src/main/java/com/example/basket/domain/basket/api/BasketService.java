package com.example.basket.domain.basket.api;

import com.example.basket.domain.basket.api.user.User;

import java.util.List;

public interface BasketService {
    BasketDto addProductToBasket(BasketDto basketDto);
    List<BasketDto> findBasketByCustomerId(String customerId);
    String removeProductFromBasket(String basketItemId);
    User getUser(String userId);
}
