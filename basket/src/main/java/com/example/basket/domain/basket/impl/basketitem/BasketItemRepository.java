package com.example.basket.domain.basket.impl.basketitem;

import com.example.basket.domain.basket.impl.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
    BasketItem findBasketItemByBasket_BasketIdAndProductId (long basketId, long productId);
    BasketItem findBasketItemByBasketItemId(long basketItemId);
}
