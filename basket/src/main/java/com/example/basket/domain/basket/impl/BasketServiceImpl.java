package com.example.basket.domain.basket.impl;

import com.example.basket.domain.basket.api.BasketDto;
import com.example.basket.domain.basket.api.BasketService;
import com.example.basket.domain.basket.api.basketitem.BasketItemDto;
import com.example.basket.domain.basket.api.product.Product;
import com.example.basket.domain.basket.api.user.User;
import com.example.basket.domain.basket.impl.basketitem.BasketItem;
import com.example.basket.domain.basket.impl.basketitem.BasketItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private static final int BASKET_THE_CURRENT_ONE = 0;
    private static final int BASKET_IS_PREPARING = 1;
    private static final int BASKET_IS_ON_THE_WAY = 2;
    private static final int BASKET_IS_DONE = 3;


    private final BasketRepository repository;
    private final BasketItemServiceImpl basketItemService;
    private static final String AUTH_URL = "http://localhost:8090/auth/users/";
    private final RestTemplate restTemplate;

    @Override
    public User getUser(String userId){
        ResponseEntity<User> user = restTemplate.getForEntity(AUTH_URL + userId, User.class);
        return user.getBody();
    }

    @Override
    public BasketDto findBasket(String userId) {
        return toDto(repository.findBasketByUserId(Long.parseLong(userId)));
    }

    @Transactional
    @Override
    public void removeProductFromBasket(String basketItemId) {
        basketItemService.delete(basketItemId);
        /*Basket basket = repository.findBasketByCustomer_CustomerIdAndStatusEquals(Integer.parseInt(customerId), BASKET_STATUS_NONE);
        List<BasketItem> basketItemList = basket.getBasketItemList();
        for (BasketItem basketItem : basketItemList){
            if (basketItem.getProduct().getProductId() == productId){
                basketItemService.delete(basketItem);
            }
        }
        basket.setBasketItemList(basketItemList);
        repository.save(basket);*/
    }

    @Override
    public BasketDto addProductToBasket(BasketDto basketDto) {
        /* if(basketDto.getBasketItemList().get(0).getProduct().getQuantity() < basketDto.getBasketItemList().get(0).getCount()){
            throw new RuntimeException("bir problem çıktıloo");
        }*/
        Basket basket = repository.findBasketByUserIdAndStatusEquals(basketDto.getUserId(), BASKET_THE_CURRENT_ONE);
        if (basket != null){
            return addProductToExistsBasket(basket, basketDto);
        }else {
            return basketDoesNotExist(basketDto);
        }
    }

    private BasketDto basketDoesNotExist(BasketDto basketDto) {
        User user = getUser(String.valueOf(basketDto.getUserId()));
        Basket basket = new Basket();
        basket.setUserId(user.getUserId());
        basket.setStatus(BASKET_THE_CURRENT_ONE);
        List<BasketItem> basketItemList = new ArrayList<>();
        Product product = basketItemService.getProduct(String.valueOf(basketDto.getBasketItemList().get(0).getProduct().getProductId()));
        for(BasketItemDto dto: basketDto.getBasketItemList()){
            BasketItem basketItem = new BasketItem();
            basketItem.setCount(dto.getCount());
            basketItem.setProductId(product.getProductId());
            basketItem.setBasketItemAmount(dto.getCount()*product.getPrice());
            basketItem.setBasket(basket);
            basketItemList.add(basketItem);
        }
        basket.setBasketItemList(basketItemList);
        basket.setTotalAmount(basket.getBasketItemList().get(0).getCount()* product.getPrice());
        basket = repository.save(basket);
        return toDto(basket);
    }

    private BasketDto addProductToExistsBasket(Basket basket, BasketDto basketDto) {
        List<BasketItem> basketItemList = basket.getBasketItemList();
        //bu satır bir basketıtemın bir sepette zaten varmı yoksa ilk defa mı eklendiğini kontrol eder
        BasketItem basketItem = basketItemService.findBasketItemByBasketIdAndProductId(basket.getBasketId(), basketDto.getBasketItemList().get(0).getProduct().getProductId());
        if (basketItem != null){
            Product product = basketItemService.getProduct(String.valueOf(basketItem.getProductId()));
            basketItem.setProductId(product.getProductId());
            basketItem.setCount(basketDto.getBasketItemList().get(0).getCount());
            basketItem.setBasketItemAmount(basketItem.getCount()*product.getPrice());
            basketItemService.save(basketItem);
        }else {
            BasketItem newBasketItem = new BasketItem();
            Product product = basketItemService.getProduct(String.valueOf(basketDto.getBasketItemList().get(0).getProduct().getProductId()));
            newBasketItem.setProductId(product.getProductId());
            newBasketItem.setCount(basketDto.getBasketItemList().get(0).getCount());
            newBasketItem.setBasketItemAmount(newBasketItem.getCount()*product.getPrice());
            newBasketItem = basketItemService.save(newBasketItem);
            newBasketItem.setBasket(basket);
            basketItemList.add(newBasketItem);
        }
        basket.setBasketItemList(basketItemList);
        basket.setTotalAmount(calculateBasketAmount(basket.getBasketId()));
        basket = repository.save(basket);
        return toDto(basket);
    }

    private double calculateBasketAmount(long basketId) {
        Basket basket = repository.findBasketByBasketId(basketId);
        double totalAmount = 0;
        for (BasketItem basketItem : basket.getBasketItemList()){
            totalAmount += basketItem.getBasketItemAmount();
        }
        return totalAmount;
    }

    private BasketDto toDto(Basket basket) {
        return BasketDto.builder()
                .basketId(basket.getBasketId())
                .userId(basket.getUserId())
                .basketItemList(basket.getBasketItemList()
                        .stream()
                        .map(basketItem -> basketItemService.toDto(basketItem))
                        .collect(Collectors.toList()))
                .totalAmount(basket.getTotalAmount())
                .build();
    }
}
