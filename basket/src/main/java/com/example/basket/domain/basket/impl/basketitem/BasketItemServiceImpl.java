package com.example.basket.domain.basket.impl.basketitem;

import com.example.basket.domain.basket.api.basketitem.BasketItemDto;
import com.example.basket.domain.basket.api.basketitem.BasketItemService;
import com.example.basket.domain.basket.api.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BasketItemServiceImpl implements BasketItemService {

    private final BasketItemRepository repository;
    private static final String PRODUCT_URL = "http://localhost:8090/stock/products/";
    private final RestTemplate restTemplate;

    public Product getProduct (String productId){
        ResponseEntity<Product> product = restTemplate.getForEntity(PRODUCT_URL + productId, Product.class);
        return product.getBody();
    }


    public BasketItem findBasketItemByBasketIdAndProductId(long basketId, long productId){
        return repository.findBasketItemByBasket_BasketIdAndProductId(basketId,productId);
    }

    public BasketItem save(BasketItem basketItem){
        return repository.save(basketItem);
    }

    public void delete(String basketItemId){
        BasketItem basketItem = repository.findById(Long.valueOf(basketItemId)).get();
        repository.delete(basketItem);
    }

    public BasketItem findBasketItemById(String basketItemId){
        BasketItem basketItem = repository.findBasketItemByBasketItemId(Long.valueOf(basketItemId));
        return basketItem;
    }

    public BasketItemDto toDto(BasketItem basketItem) {
        Product product = getProduct(String.valueOf(basketItem.getProductId()));
        return BasketItemDto.builder()
                .basketItemId(basketItem.getBasketItemId())
                .basketItemAmount(basketItem.getBasketItemAmount())
                .count(basketItem.getCount())
                .product(product)
                .build();
    }
}
