package org.example.myrefrigerator.product.service;

import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.product.dto.ProductCreateRequest;
import org.example.myrefrigerator.product.dto.ProductResponse;
import org.example.myrefrigerator.product.entity.Product;
import org.example.myrefrigerator.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(ProductCreateRequest request) {
        if (productRepository.existsByNameAndCategory(request.name(), request.category())) {
            throw new IllegalArgumentException("Product already exist");
        }
        Product product = Product.create(request.name(), request.category());
        productRepository.save(product);
    }

    public List<ProductResponse> getProducts(){
        return productRepository.findAll().stream().map(ProductResponse::from).toList();
    }

}
