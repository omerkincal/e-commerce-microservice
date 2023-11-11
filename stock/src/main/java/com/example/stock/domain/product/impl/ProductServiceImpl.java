package com.example.stock.domain.product.impl;

import com.example.stock.domain.category.api.CategoryDto;
import com.example.stock.domain.category.api.CategoryService;
import com.example.stock.domain.product.api.ProductDto;
import com.example.stock.domain.product.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final CategoryService categoryService;

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = toEntity(productDto);
        product = repository.save(product);
        return toDto(product);
    }

    @Override
    public ProductDto getProduct(String productId) {
        Product product = getProductEntity(productId);
        return toBasketProductDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String productId) {
        Product product = getProductEntity(productId);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCategory(categoryService.getCategoryEntity(String.valueOf(productDto.getCategoryDto().getCategoryId())));
        product = repository.save(product);
        return toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(product -> toDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = getProductEntity(productId);
        repository.delete(product);
    }


    public ProductDto toDto(Product entity){
        return ProductDto.builder()
                .productId(entity.getProductId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .categoryDto(CategoryDto.builder()
                        .categoryId(entity.getCategory().getCategoryId())
                        .categoryName(entity.getCategory().getCategoryName())
                        .build())
                .build();
    }

    public ProductDto toBasketProductDto(Product entity){
        return ProductDto.builder()
                .productId(entity.getProductId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }


    public Product toEntity(ProductDto dto){
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());
        entity.setCategory(categoryService.getCategoryEntity(String.valueOf(dto.getCategoryDto().getCategoryId())));
        return entity;
    }


    public Product getProductEntity(String productId){
        return repository.findById(Long.valueOf(productId)).get();
    }
}
