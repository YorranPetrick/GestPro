package com.yo.GestPro.controller;

import com.yo.GestPro.infra.filter.RequestFilter;
import com.yo.GestPro.infra.security.TokenJwt;
import com.yo.GestPro.models.product.ConsumeProductDto;
import com.yo.GestPro.models.product.CreateProductDto;
import com.yo.GestPro.service.product.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final TokenJwt jwt;

    @PostMapping
    private ResponseEntity<Void> createProduct(HttpServletRequest httpRequest, @RequestBody CreateProductDto createProductDto){
        String token = RequestFilter.recoverToken(httpRequest);
        String subject = jwt.getSubject(token);

        productService.createProduct(subject, createProductDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private ResponseEntity<?> consumeProduct(@RequestBody ConsumeProductDto ConsumeProductDto){
        var response = productService.consumeProduct(ConsumeProductDto.idProduct(), ConsumeProductDto.quantity());
        if (response!= null){
            return response;

        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Error, please check with support.");
        }
    }

    @PutMapping
    private ResponseEntity<?> updateProduct(@RequestParam String uuid, @RequestBody CreateProductDto createProductDto){
       return productService.updateProduct(uuid, createProductDto);

    }
}
