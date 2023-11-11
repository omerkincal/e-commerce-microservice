package com.example.basket.domain.basket.web;

import com.example.basket.domain.basket.api.BasketDto;
import com.example.basket.domain.basket.api.BasketService;
import com.example.basket.domain.basket.api.basketitem.BasketItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("basket")
@RequiredArgsConstructor
public class BasketController {


    private final BasketService service;

    @PostMapping("add-product-to-basket")
    public ResponseEntity<BasketResponse> addProductToBasket(@RequestBody AddProductRequest request){
        return new ResponseEntity<>(toResponse(service.addProductToBasket(request.toDto())), HttpStatus.OK);
    }

    /*@GetMapping("{customerId}")
    public BasketResponse getBasketById(@PathVariable String customerId){
        return toResponse(service.getBasketById(customerId));
    }*/

    @DeleteMapping("{basketItemId}")
    public String delete(@PathVariable String basketItemId){
        return service.removeProductFromBasket(basketItemId);
    }


    public BasketResponse toResponse(BasketDto basketDto){



         List<BasketItemDto> basketItemList;
        return BasketResponse.builder()
                .basketId(basketDto.getBasketId())
                .user(service.getUser(String.valueOf(basketDto.getUserId())))
                .totalAmount(basketDto.getTotalAmount())
                .basketItemList(basketDto.getBasketItemList())
                .status(basketDto.getStatus())
                .build();
    }
}
