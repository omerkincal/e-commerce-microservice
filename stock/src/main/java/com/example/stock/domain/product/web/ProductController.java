package com.example.stock.domain.product.web;

import com.example.stock.domain.product.api.ProductDto;
import com.example.stock.domain.product.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest request){
        ProductDto productDto = request.toDto();
        productDto = service.saveProduct(productDto);
        return new ResponseEntity<>(toResponse(productDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(name = "id") String productId){
        return new ResponseEntity<>(toResponse(service.getProduct(productId)),HttpStatus.FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest request,
                                                         @PathVariable(name = "id") String productId){
        return new ResponseEntity<>(toResponse(service.updateProduct(request.toDto(),productId)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id")String productId) {
        try {
            service.deleteProduct(productId);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts()
                .stream()
                .map(productDto -> toResponse(productDto))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    private ProductResponse toResponse(ProductDto productDto) {
        return ProductResponse.builder()
                .productId(productDto.getProductId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .categoryDto(productDto.getCategoryDto())
                .build();
    }
}
