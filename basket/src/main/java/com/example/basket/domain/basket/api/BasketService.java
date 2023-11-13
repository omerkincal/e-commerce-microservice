package com.example.basket.domain.basket.api;

import com.example.basket.domain.basket.api.user.User;

import java.util.List;

public interface BasketService {
    BasketDto addProductToBasket(BasketDto basketDto);
    BasketDto findBasket(String userId);
    void removeProductFromBasket(String productId);
    User getUser(String userId);
}
