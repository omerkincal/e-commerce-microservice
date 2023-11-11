package com.example.stock.domain.product.impl;

import com.example.stock.domain.category.impl.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    @Column(name = "product_name")
    private String name;
    @Column(name = "stock")
    private int stock;
    @Column(name = "price")
    private double price;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "categoryId")
    private Category category;
}
