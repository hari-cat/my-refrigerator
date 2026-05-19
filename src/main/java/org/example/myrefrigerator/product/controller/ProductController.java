package org.example.myrefrigerator.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.product.dto.ProductCreateRequest;
import org.example.myrefrigerator.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductCreateRequest request){
        productService.createProduct(request);
        return ResponseEntity.ok("Product register success");
    }


}
