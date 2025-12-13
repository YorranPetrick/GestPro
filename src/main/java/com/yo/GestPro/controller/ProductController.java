package com.yo.GestPro.controller;

import com.yo.GestPro.models.product.CreateProductDto;
import com.yo.GestPro.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    private ResponseEntity<Void> createProduct(CreateProductDto createProductDto){
        productService.createProduct(createProductDto);
        return ResponseEntity.ok().build();
    }
}
