package com.example.stock.domain.product.api;

import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);
    ProductDto getProduct(String productId);
    ProductDto updateProduct(ProductDto productDto, String productId);
    List<ProductDto> getAllProducts();
    void deleteProduct(String productId);


}
