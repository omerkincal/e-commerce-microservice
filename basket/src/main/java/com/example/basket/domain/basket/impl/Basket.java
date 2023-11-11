package com.example.basket.domain.basket.impl;

import com.example.basket.domain.basket.impl.basketitem.BasketItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long basketId;
    private double totalAmount;
    private int status;
    @Column(name = "user_id")
    private long userId;
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<BasketItem> basketItemList;
}
