package org.example.myrefrigerator.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.product.dto.ProductCreateRequest;
import org.example.myrefrigerator.product.dto.ProductResponse;
import org.example.myrefrigerator.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }
}
