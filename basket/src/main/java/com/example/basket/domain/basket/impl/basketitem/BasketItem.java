package com.example.basket.domain.basket.impl.basketitem;

import com.example.basket.domain.basket.impl.Basket;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long basketItemId;
    private double basketItemAmount;
    private int count;
    private long productId;
    @ManyToOne
    @JoinColumn(name = "basketId")
    private Basket basket;
}
